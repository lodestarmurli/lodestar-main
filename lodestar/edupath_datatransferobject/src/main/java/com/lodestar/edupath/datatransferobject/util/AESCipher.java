package com.lodestar.edupath.datatransferobject.util;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public final class AESCipher
{
	private AESCipher()
	{
		// do anything
	}

	public static byte[] encrypt(byte[] data) throws GeneralSecurityException
	{
		return encrypt("edupathgesohyes", data);
	}

	/**
	 * @param password
	 * @param data
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static byte[] encrypt(String password, byte[] data) throws GeneralSecurityException
	{
        
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), password.getBytes(), 1024, 128);
		SecretKey generateSecret = factory.generateSecret(spec);
		SecretKey secretKey = new SecretKeySpec(generateSecret.getEncoded(), "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] encryptedKey = generateSecret.getEncoded();

		AlgorithmParameters params = cipher.getParameters();
		byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
		byte[] cipherData = cipher.doFinal(data);
		byte[] finalData = new byte[encryptedKey.length + iv.length + cipherData.length];

		System.arraycopy(encryptedKey, 0, finalData, 0, encryptedKey.length);
		System.arraycopy(iv, 0, finalData, encryptedKey.length, iv.length);
		System.arraycopy(cipherData, 0, finalData, iv.length + encryptedKey.length, cipherData.length);

		return finalData;
	}

	/**
	 * @param encryptedData
	 * @return
	 * @throws GeneralSecurityException
	 */
	public static byte[] decrypt(byte[] encryptedData) throws GeneralSecurityException
	{
		byte[] keyBytes = new byte[16];
		for (int i = 0; i < keyBytes.length; i++)
		{
			keyBytes[i] = encryptedData[i];
		}
		SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
		byte[] completeData = encryptedData;
		byte[] iv = new byte[16];
		byte[] data = new byte[completeData.length - iv.length - keyBytes.length];

		System.arraycopy(completeData, 0, keyBytes, 0, keyBytes.length);
		System.arraycopy(completeData, keyBytes.length, iv, 0, iv.length);
		System.arraycopy(completeData, keyBytes.length + iv.length, data, 0, data.length);

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
		return cipher.doFinal(data);
	}
}