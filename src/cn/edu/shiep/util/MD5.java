package cn.edu.shiep.util;

import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class MD5 {
	
	private static Logger log = Logger.getLogger(MD5.class);

	public static String encrypt(String x) {
		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.update(x.getBytes("UTF8"));
			byte s[] = m.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00)
						.substring(6);
			}
			return result;
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return null;
		}
	}
}
