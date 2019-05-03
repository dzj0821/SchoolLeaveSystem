package pers.dzj0821.SchoolLeaveSystem.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import pers.dzj0821.SchoolLeaveSystem.Messages;

public class SHA256Util {
	private static Logger logger = LogManager.getLogger(SHA256Util.class);
	private static MessageDigest digest;
	
	static {
		try {
			digest = MessageDigest.getInstance("SHA-256"); //$NON-NLS-1$
		} catch (NoSuchAlgorithmException e) {
			logger.error(Messages.getString("SHA256InitFailed"), e); //$NON-NLS-1$
			throw new Error(Messages.getString("SHA256InitFailed"), e); //$NON-NLS-1$
		}
	}
	
	public static String encrypt(String content) {
		digest.reset();
		byte[] bytes = digest.digest(content.getBytes());
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < bytes.length; i++) {
			if((bytes[i] & 0xFF) < 16) {
				builder.append(0);
			}
			builder.append(Integer.toHexString(bytes[i] & 0xFF));
		}
		return builder.toString();
	}
}
