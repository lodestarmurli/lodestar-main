/*
 * @(#) EmailGenerator.java  
 * 
 * Licensed Materials - Property of JaMocha Tech
 * (c) Copyright JaMochaTech Private Limited 2009, 2009. ALL RIGHTS RESERVED
 *
 * #730, 2nd Floor, 3rd Block, Koramangala, Bengaluru-560034, India 
 *
 * This software is the confidential and proprietary information of
 * JaMocha Tech Private Limited ("Confidential Information").
 * You shall not disclose such 'Confidential Information' and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with JaMocha Tech Private Limited.
 *
 * @Version 1.0 
 * @Date Jul 21, 2009	
 * @Author Nikhil.R
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.email;

import java.io.File;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.util.ApplicationProperties;

public class EmailGenerator
{
	private static final Logger		OUT							= LoggerFactory.getLogger(EmailGenerator.class);	//$NON-NLS-1$

	private static EmailPacket		emailPacket;
	private static final int		AUTO_SELECT					= 3;
	private static final int		HTML						= 2;
	private static final int		PLAIN						= 1;
	private static int				emailServerTimeoutInMillis	= 5000;
	private static String[]			validUnSentAddress;
	private static EMailSettings	emailSettings				= EMailSettings.getInstance();
	private static final int		MAX_RECIPIENTS_SIZE			= 15;

	/**
	 * @param localEmailPacket
	 * @return
	 * @throws Exception
	 */
	public static boolean send(EmailPacket localEmailPacket) throws Exception
	{
		EmailGenerator.emailPacket = localEmailPacket;

		String mailServer = emailSettings.getSmtpServer();
		String fromAddr = emailSettings.getSmtpFromAddress();
		String encryptionType = emailSettings.getEncryptionType();
		int port = emailSettings.getSmtpServerPort();
		if ("".equals(emailSettings.getSmtpServer()) || emailSettings.getSmtpServer() == null)
		{
			OUT.warn("Email Server not configured.");
			return false;
		}

		String[] to = emailPacket.getTo();
		if ("HTML".equals(emailPacket.mime))
		{
			String[] alternativeMessages = new String[]
			{
					null,
					emailPacket.getBody()
			};
			if (emailPacket.getImageFileList() != null && !emailPacket.getImageFileList().isEmpty())
			{
				List<String> imageFileList = emailPacket.getImageFileList();
				imageFileList.add(0, null);
				imageFileList.add(1, emailPacket.getBody());
				alternativeMessages = imageFileList.toArray(new String[0]);
			}
			return postAlternativeMail(HTML, mailServer, port, to, emailPacket.getCc(), emailPacket.getSubject(), alternativeMessages, fromAddr,
					emailPacket.getAttachments(), encryptionType);
		}
		else if ("TEXT".equals(emailPacket.mime))
		{
			return postAlternativeMail(PLAIN, mailServer, port, to, emailPacket.getCc(), emailPacket.getSubject(), new String[]
			{
				emailPacket.getBody()
			}, fromAddr, emailPacket.getAttachments(), encryptionType);
		}
		else
		{
			return postAlternativeMail(AUTO_SELECT, mailServer, port, to, emailPacket.getCc(), emailPacket.getSubject(), new String[]
			{
				emailPacket.getBody()
			}, fromAddr, emailPacket.getAttachments(), encryptionType);
		}
	}

	/**
	 * @param mailServer
	 * @param from
	 * @param serverPort
	 * @return
	 */
	private static Session getMailSession(String mailServer, String from, int serverPort, String encryptionType)
	{
		Properties props = System.getProperties();
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.port", serverPort);
		props.put("mail.smtp.connectiontimeout", 120000);
		props.put("mail.smtp.timeout", 120000);

		OUT.debug("Email Encryption type : " + encryptionType);
		if ("TLS".equals(encryptionType))
		{
			props.put("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.ssl.enable", "true");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
		}
		else if ("SSL".equals(encryptionType))
		{
			props.setProperty("mail.smtp.ssl.enable", "true");
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
		}
		else
		{
			props.setProperty("mail.smtp.ssl.enable", "false");
			props.put("mail.smtp.starttls.enable", "false");
			props.remove("mail.smtp.socketFactory.class");
			props.setProperty("mail.smtp.auth.plain.disable", "true");
			props.put("mail.smtp.socketFactory.fallback", "true");
		}
		// props.put("mail.debug", "true");
		Session mailSession = null;
		if (emailSettings.isSMTPAuthenticationRequired())
		{
			CustomAuthentication auth = new CustomAuthentication(emailSettings.getSmtpUserName(), emailSettings.getSmtpPassword());
			if ("SSL".equals(encryptionType) || "TLS".equals(encryptionType))
			{
				props.setProperty("mail.smtps.auth", "true");
			}
			else
			{
				props.setProperty("mail.smtp.auth", "true");
			}
			mailSession = Session.getInstance(props, auth);
		}
		else
		{
			mailSession = Session.getDefaultInstance(props, null);
		}
		mailSession.setDebug(false);
		return mailSession;
	}

	/**
	 * @param ex
	 */
	private static void handleMessagingExceptions(MessagingException ex)
	{
		do
		{
			try
			{
				ex.printStackTrace();
				if (ex instanceof SendFailedException)
				{
					SendFailedException sfex = (SendFailedException) ex;
					Address[] invalid = sfex.getInvalidAddresses();
					Address[] validUnsent = sfex.getValidUnsentAddresses();
					Address[] validSent = sfex.getValidSentAddresses();
					if (invalid != null)
					{
						OUT.debug("    ** Invalid Addresses");
						for (Address element : invalid)
						{
							OUT.debug("         " + element);
						}
					}
					if (validUnsent != null)
					{
						OUT.debug("    ** ValidUnsent Addresses");
						validUnSentAddress = new String[validUnsent.length];
						for (int i = 0; i < validUnsent.length; i++)
						{
							OUT.debug("         " + validUnsent[i]);
							validUnSentAddress[i] = validUnsent[i] + "";
						}
					}
					if (validSent != null)
					{
						OUT.debug("    ** ValidSent Addresses");
						for (Address element : validSent)
						{
							OUT.debug("         " + element);
						}
					}
					else
					{
						OUT.info("", ex);
					}
				}
				if (ex.getNextException() instanceof MessagingException)
				{
					ex = (MessagingException) ex.getNextException();
					OUT.debug("Email Server is not reachable");
				}
				else
				{
					ex = null;
				}
			}
			catch (Exception e)
			{
				OUT.error("Exception", e);
			}
		}
		while (ex != null);
	}

	/**
	 * @param type
	 * @param mailServer
	 * @param serverPort
	 * @param recipients
	 * @param subject
	 * @param alternativeMessages
	 * @param from
	 * @param attachments
	 * @return
	 * @throws Exception
	 */
	private static boolean postAlternativeMail(int type, String mailServer, int serverPort, String recipients[], String ccRecipeints[], String subject,
			String[] alternativeMessages, String from, File[] attachments, String encryptionType) throws Exception
	{
		boolean isSuccess = false;
		if (from != null)
		{
			if (recipients != null && recipients.length > 0)
			{
				boolean isContinueExcesRecipient = false;
				int addressToLength = 0;
				int prevAddressToLength = 0;
				int recipientsLength = recipients.length;

				InternetAddress[] ccList = null;
				if (ccRecipeints != null)
				{
					ccList = new InternetAddress[ccRecipeints.length];
					for (int i = 0; i < ccRecipeints.length; i++)
					{
						ccList[i] = new InternetAddress(ccRecipeints[i]);
					}
				}

				while (recipientsLength > 0)
				{
					OUT.debug("Sending Notification Messages for batch {}", prevAddressToLength);
					isSuccess = false;
					isContinueExcesRecipient = true;
					prevAddressToLength = addressToLength;
					InternetAddress[] addressTo;
					if (recipientsLength >= MAX_RECIPIENTS_SIZE)
					{
						addressToLength = addressToLength + MAX_RECIPIENTS_SIZE;
						addressTo = new InternetAddress[MAX_RECIPIENTS_SIZE];
					}
					else
					{
						addressToLength = addressToLength + recipientsLength;
						addressTo = new InternetAddress[recipientsLength];
					}
					Session session = getMailSession(mailServer, from, serverPort, encryptionType);
					Message msg = new MimeMessage(session);

					InternetAddress addressFrom = new InternetAddress(from);
					msg.setFrom(addressFrom);
					msg.setSentDate(new Date());

					int index = 0;
					for (int i = prevAddressToLength; i < addressToLength; i++)
					{
						addressTo[index] = new InternetAddress(recipients[i]);
						index = index + 1;
					}
					msg.setRecipients(Message.RecipientType.TO, addressTo);
					recipientsLength = recipientsLength - MAX_RECIPIENTS_SIZE;

					if (ccList != null && ccList.length > 0)
					{
						msg.setRecipients(Message.RecipientType.CC, ccList);
					}

					msg.setSubject(subject);

					MimeBodyPart contentRoot = new MimeBodyPart();
					MimeMultipart alternativeParts = new MimeMultipart("alternative");
					MimeMultipart mixedParts = new MimeMultipart("mixed");

					String message = "";
					if (type == PLAIN)
					{
						if (alternativeMessages != null && alternativeMessages[0] != null && alternativeMessages[0].length() != 0)
						{
							message = alternativeMessages[0];
						}

						MimeBodyPart textBody = new MimeBodyPart();
						textBody.setContent(message, "text/plain");
						alternativeParts.addBodyPart(textBody);
					}
					else if (alternativeMessages != null && alternativeMessages.length >= 2)
					{
						MimeBodyPart htmlBody = new MimeBodyPart();
						htmlBody.setContent(alternativeMessages[1], "text/html");
						alternativeParts.addBodyPart(htmlBody);
						if (alternativeMessages.length > 2)
						{
							alternativeParts.setSubType("related");
							for (int i = 2; i < alternativeMessages.length; i++)
							{
								if (ApplicationProperties.getInstance().getProperty("databaseroot") != null) // issue
								{
									MimeBodyPart imageBody = new MimeBodyPart();
									FileDataSource fds = new FileDataSource(alternativeMessages[i]);
									imageBody.setFileName(fds.getName());
									imageBody.setDataHandler(new DataHandler(fds));
									imageBody.setHeader("Content-ID", "<" + fds.getName() + "@prohance.com>");
									alternativeParts.addBodyPart(imageBody);
								}
							}
						}
					}

					contentRoot.setContent(alternativeParts);
					mixedParts.addBodyPart(contentRoot);
					if (attachments != null)
					{
						for (File attachement : attachments)
						{
							MimeBodyPart mimeBodyPart = new MimeBodyPart();
							mimeBodyPart.setDisposition(Part.ATTACHMENT);
							mimeBodyPart.setDataHandler(new DataHandler(new FileDataSource(attachement)));
							mimeBodyPart.setFileName(attachement.getName());
							mixedParts.addBodyPart(mimeBodyPart);
						}
					}

					msg.setContent(mixedParts);
					long timeoutBegin = System.currentTimeMillis();
					Exception ex = null;
					try
					{
						while ((System.currentTimeMillis() - timeoutBegin) < emailServerTimeoutInMillis && (!isSuccess || isContinueExcesRecipient))
						{
							Transport tp = null;
							if ("TLS".equals(encryptionType) || "SSL".equals(encryptionType))
							{
								tp = session.getTransport("smtps");
							}
							else
							{
								tp = session.getTransport("smtp");
							}
							try
							{
								OUT.info("Posting Mail to " + Arrays.toString(recipients) + ", CCList: " + Arrays.toString(ccRecipeints) + " with subject: "
										+ subject + ", TP : " + tp.toString());
								if (emailSettings.isSMTPAuthenticationRequired())
								{
									OUT.debug(emailSettings.getSmtpServer() + "  " + emailSettings.getSmtpUserName() + " with password");
									tp.connect(emailSettings.getSmtpServer(), emailSettings.getSmtpUserName(), emailSettings.getSmtpPassword());
								}
								else
								{
									tp.connect();
								}
								tp.sendMessage(msg, msg.getAllRecipients());
								OUT.info("Mail sent to " + Arrays.toString(recipients) + ", CCList: " + Arrays.toString(ccRecipeints) + " with subject: " + subject);
								ex = null;
								isSuccess = true;
							}
							catch (MessagingException e)
							{
								ex = e;
								if (e.getNextException() instanceof UnknownHostException)
								{
									throw new UnknownHostException("Email Server is not reachable.");
								}
								handleMessagingExceptions(e);
								OUT.debug("Message send failed! Retrying transmission");
								Thread.sleep(500);
							}
							catch (Exception e)
							{
								OUT.error("Exception", e);
							}
							finally
							{
								tp.close();
							}
							isContinueExcesRecipient = false;
						}
					}
					catch (UnknownHostException e)
					{
						ex = e;
					}
					catch (RuntimeException e)
					{
						ex = e;
						OUT.error("Exception", e);
					}
					if (ex != null)
					{
						throw ex;
					}
				}
			}
		}
		else
		{
			OUT.debug("Error: From Address not set!");
		}
		return isSuccess;
	}

	static class CustomAuthentication extends Authenticator
	{
		String	authUser;
		String	authPwd;

		public CustomAuthentication(String authUser, String authPwd)
		{
			this.authUser = authUser;
			this.authPwd = authPwd;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication()
		{
			return new PasswordAuthentication(authUser, authPwd);
		}
	}

	public static void main(String[] args) throws Exception
	{
		send(new EmailPacket("kiran.rs@jamochatech.com", null, "message", "test mail"));
	}

}