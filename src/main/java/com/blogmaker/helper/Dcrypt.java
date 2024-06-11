package com.blogmaker.helper;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;
@Component
public class Dcrypt 
{
	 	private static final String key = "fasdadaadertfefd"; // 16 bytes
	    private static final String initVector = "opikjuyhgtrfdesc"; // 16 bytes
	    
	    
	    public static String decrypt(String encrypted) throws Exception {
	        IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

	        byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
	        return new String(original);
	    } 
	    
	    
	    
}
