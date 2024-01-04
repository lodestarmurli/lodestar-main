/*
 * @(#) CommonUtil.java   
 * 
 * Licensed Materials - Property of JaMocha Tech
 * (c) Copyright JaMochaTech Private Limited 2009, 2013. ALL RIGHTS RESERVED
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
 * @Date Mar 05, 2013	
 * @Author Pushpinder Singh
 *
 * Code Change Control:
 * Date                     Developer                           Remarks
 * ----------               ------------------                  -------------------
 * dd/mm/yyyy               <Developer's Name>                  <Reason for change>
 *
 */
package com.lodestar.edupath.datatransferobject.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class CommonUtil
{
	public static boolean deleteDir(File dir)
	{
		if (dir.isDirectory())
		{
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++)
			{
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success)
				{
					return false;
				}
			}
		}
		// The directory is now empty so delete it
		return dir.delete();
	}

	public static String convertArrayToCommaString(Collection<Integer> list)
	{
		StringBuilder commaSeprated = new StringBuilder();
		if (list != null)
		{
			for (Integer i : list)
			{
				if (i == null)
				{
					continue;
				}

				if (commaSeprated.length() < 1)
				{
					commaSeprated.append(i);
				}
				else
				{
					commaSeprated.append(",").append(i);
				}
			}
		}
		return commaSeprated.toString();
	}

	public static String convertStringArrayToCommaString(Collection<String> list)
	{
		StringBuilder commaSeprated = new StringBuilder();
		if (list != null)
		{
			for (String i : list)
			{
				if (commaSeprated.length() < 1)
				{
					commaSeprated.append("'").append(i).append("'");
				}
				else
				{
					commaSeprated.append(", '").append(i).append("'");
				}
			}
		}
		return commaSeprated.toString();
	}

	public static String convertStringArrayToCommaString(Collection<String> list, boolean isquoteRequired)
	{
		StringBuilder commaSeprated = new StringBuilder();
		if (list != null)
		{
			for (String i : list)
			{
				if (commaSeprated.length() < 1)
				{
					if (isquoteRequired)
					{
						commaSeprated.append("'").append(i).append("'");
					}
					else
					{
						commaSeprated.append(i);
					}
				}
				else
				{
					if (isquoteRequired)
					{
						commaSeprated.append(", '").append(i).append("'");
					}
					else
					{
						commaSeprated.append(",").append(i).append("");
					}
				}
			}
		}
		return commaSeprated.toString();
	}

	/**
	 * @param aStart
	 * @param aEnd
	 * @return
	 */
	public static long generateRandomNumber(int aStart, long aEnd)
	{
		if (aStart > aEnd)
		{
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		// get the range, casting to long to avoid overflow problems
		long range = aEnd - (long) aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long) (range * new Random().nextDouble());
		long randomNumber = fraction + (long) aStart;
		return randomNumber;
	}

	/**
	 * @param time1
	 * @param time2
	 * @param choice
	 * @return
	 */
	public static int getDiffBetweenDatesByChoice(long time1, long time2, char choice)
	{
		long diffInMillis = time2 - time1;
		int result = 0;
		if (diffInMillis <= 0)
		{
			return 0;
		}
		switch (choice)
		{
			case 'S': // Seconds
				result = (int) (diffInMillis / 1000);
				break;
			case 'M': // Minutes
				result = (int) (diffInMillis / (60 * 1000));
				break;
			case 'H': // Hours
				result = (int) (diffInMillis / (60 * 60 * 1000));
				break;
			case 'D': // Days
				result = (int) (diffInMillis / (24 * 60 * 60 * 1000));
				break;
			default:
				result = 0;
				break;
		}
		return result;
	}

	/**
	 * Replace all XML specific symbols to characters, to avoid script injection
	 * issue.
	 * 
	 * @param aText
	 * @return
	 */
	public static String replaceXMLEntities(String aText)
	{
		if (aText == null)
			return aText;
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE)
		{
			if (character == '&')
			{
				result.append("&amp;");
			}
			else if (character == '<')
			{
				result.append("&lt;");
			}
			else if (character == '>')
			{
				result.append("&gt;");
			}
			else if (character == '\"')
			{
				result.append("&quot;");
			}
			else if (character == '\'')
			{
				result.append("&#039;");
			}
			else
			{
				// the char is not a special one add it to the
				// result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();

	}

	/**
	 * Escaping single quote with double single quote, to avoid database
	 * injection issue.
	 * 
	 * @param aText
	 * @return
	 */
	public static String replaceDBQuotes(String aText)
	{

		if (aText == null)
			return aText;
		final StringBuilder result = new StringBuilder();
		final StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != CharacterIterator.DONE)
		{
			if (character == '\'')
			{
				result.append("''");
			}
			else
			{
				// the char is not a special one add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	/**
	 * @param sourceInByte
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resizeImage(byte[] sourceInByte, int width, int height)
	{
		Graphics2D graphics = null;
		BufferedImage finalImg = null;
		try
		{
			ByteArrayInputStream inputStream = new ByteArrayInputStream(sourceInByte);
			BufferedImage source = ImageIO.read(inputStream);
			Image img = source.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
			finalImg = new BufferedImage(width, height, source.getType());
			graphics = finalImg.createGraphics();
			graphics.drawImage(img, 0, 0, width, height, null);
		}
		catch (IOException e)
		{
		}
		finally
		{
			if (graphics != null)
				graphics.dispose();
		}
		return finalImg;
	}

	/**
	 * @param image
	 * @param imageFormat
	 * @return
	 */
	public static byte[] getByteArrayFromImage(BufferedImage image, String imageFormat)
	{
		ByteArrayOutputStream bos = null;
		try
		{
			bos = new ByteArrayOutputStream();
			ImageIO.write(image, imageFormat, bos);
			bos.flush();
		}
		catch (IOException e)
		{
		}
		return bos.toByteArray();
	}

	/**
	 * @param commaSeprated
	 * @return
	 */
	public static List<String> convertCommaSeperatedToList(String commaSeprated)
	{
		if (commaSeprated == null || commaSeprated.equals(""))
		{
			return null;
		}
		String[] commaSepratedArray = commaSeprated.split(",");
		List<String> stringSet = new ArrayList<String>();
		for (int i = 0; i < commaSepratedArray.length; i++)
		{
			stringSet.add(commaSepratedArray[i].trim());
		}
		return stringSet;
	}

	/**
	 * @param commaSeprated
	 * @return
	 */
	public static List<Integer> convertCommaSeperatedToIntegerList(String commaSeprated)
	{
		if (commaSeprated == null || commaSeprated.equals(""))
		{
			return null;
		}
		String[] commaSepratedArray = commaSeprated.split(",");
		List<Integer> integerSet = new ArrayList<Integer>();
		for (int i = 0; i < commaSepratedArray.length; i++)
		{
			integerSet.add(Integer.parseInt(commaSepratedArray[i]));
		}
		return integerSet;
	}

	/**
	 * @param commaSeprated
	 * @return
	 */
	public static List<Double> convertCommaSeperatedToDoubleList(String commaSeprated)
	{
		if (commaSeprated == null || commaSeprated.equals(""))
		{
			return null;
		}
		String[] commaSepratedArray = commaSeprated.split(",");
		List<Double> doubleList = new ArrayList<Double>();
		for (int i = 0; i < commaSepratedArray.length; i++)
		{
			doubleList.add(Double.valueOf(commaSepratedArray[i]));
		}
		return doubleList;
	}

	/**
	 * @param commaSeprated
	 * @return
	 */
	public static List<String> convertSpaceNOthersSeperatedToList(String spaceSeprated)
	{
		if (spaceSeprated == null || spaceSeprated.equals(""))
		{
			return null;
		}
		String[] spaceSepratedArray = spaceSeprated.split("[;,\\s\\.]+");
		List<String> stringList = new ArrayList<String>();
		for (int i = 0; i < spaceSepratedArray.length; i++)
		{
			if (!spaceSepratedArray[i].trim().isEmpty())
			{
				stringList.add(spaceSepratedArray[i].trim());
			}
		}
		return stringList;
	}

	public static String replaceJSONEntities(String aText)
	{
		final StringBuilder result = new StringBuilder();
		StringCharacterIterator iterator = new StringCharacterIterator(aText);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE)
		{
			if (character == '\"')
			{
				result.append("\\\"");
			}
			else if (character == '\\')
			{
				result.append("\\\\");
			}
			else if (character == '/')
			{
				result.append("\\/");
			}
			else if (character == '\b')
			{
				result.append("\\b");
			}
			else if (character == '\f')
			{
				result.append("\\f");
			}
			else if (character == '\n')
			{
				result.append("\\n");
			}
			else if (character == '\r')
			{
				result.append("\\r");
			}
			else if (character == '\t')
			{
				result.append("\\t");
			}
			else if (character == '\'')
			{
				result.append("\\'");
			}
			else
			{
				// the char is not a special one
				// add it to the result as is
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	/*
	 * public static String replaceBackXMLEntities(String aString)
	 * {
	 * final StringBuilder result = new StringBuilder();
	 * result.append(StringUtils.replace(aString, "&lt;", "<", -1));
	 * result.replace(0, aString.length(), StringUtils.replace(result.toString(), "&gt;", ">", -1));
	 * result.replace(0, aString.length(), StringUtils.replace(result.toString(), "&quot;", "\"", -1));
	 * result.replace(0, aString.length(), StringUtils.replace(result.toString(), "&#039;", "\'", -1));
	 * result.replace(0, aString.length(), StringUtils.replace(result.toString(), "&amp;", "&", -1));
	 * return result.toString();
	 * 
	 * }
	 */

	/**
	 * @param pageSize
	 * @param maxResult
	 * @return
	 */
	public static int getSkipResult(int pageSize, int maxResult)
	{
		return pageSize * maxResult;
	}

	public static int getSumOfChar(String raisecCode) throws Exception
	{
		char[] raisecCodeArr = raisecCode.trim().toLowerCase().toCharArray();
		int sum = 0;
		for (char c : raisecCodeArr)
		{
			sum += c;
		}
		return sum;
	}

	public static String getExitRAISECCode(int oldRaisecCode, List<String> list, boolean isCaseSensitive) throws Exception
	{
		String newCode;
		for (String code : list)
		{
			newCode = code;
			if (isCaseSensitive)
			{
				newCode = newCode.toLowerCase();
			}
			if (oldRaisecCode == getSumOfChar(newCode))
			{
				return code;
			}
		}
		return null;
	}
}
