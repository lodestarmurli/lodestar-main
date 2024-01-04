package com.lodestar.edupath.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lodestar.edupath.datatransferobject.util.ApplicationConstants;

public class EncryptionDecryptionData {
	private static final Logger	OUT	= LoggerFactory.getLogger(EncryptionDecryptionData.class);
	
	private static final String key ="d7lynlpsTkOwwfzkd7lynlpsTkOwwfzkd7lynlpsTkOw";
	public String Decrypt(String tokendata)
	{
		
		try
		{
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] keyBytes= new byte[16];
			byte[] b= key.getBytes("UTF-8");
			int len= b.length;
			if (len > keyBytes.length) len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
			cipher.init(Cipher.DECRYPT_MODE,keySpec,ivSpec);
	
	
			byte [] results = cipher.doFinal(Base64.decodeBase64(tokendata));
			return new String(results,"UTF-8");
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			
		}
		
		return null;
	}
	public String Encrypt(String tokendata)
	{
		
		try
		{
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			byte[] keyBytes= new byte[16];
			byte[] b= key.getBytes("UTF-8");
			int len= b.length;
			if (len > keyBytes.length) len = keyBytes.length;
			System.arraycopy(b, 0, keyBytes, 0, len);
			SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
			IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
			cipher.init(Cipher.ENCRYPT_MODE,keySpec,ivSpec);

			byte[] results = cipher.doFinal(tokendata.getBytes("UTF-8"));
			
			return new String(Base64.encodeBase64URLSafeString(results));
		
		}
		catch (Exception e)
		{
			OUT.error(ApplicationConstants.EXCEPTION, e);
			
		}
		
		return null;
	}
	
	
	
	
	
}
