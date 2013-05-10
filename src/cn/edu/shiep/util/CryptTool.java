package cn.edu.shiep.util;

import java.util.Random;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * CryptTool ��װ��һЩ���ܹ��߷���, ���� 3DES, MD5 ��.
 * 
 * @author hxq
 * @version 1.0 2006-01-10
 */
public class CryptTool {

	public CryptTool() {
	}
 
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "A", "B", "C", "D", "E", "F" };
	

	/**
	 * ת���ֽ�����Ϊ16�����ִ�
	 * 
	 * @param b
	 *            �ֽ�����
	 * @return 16�����ִ�
	 */

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString().toUpperCase();
	}
	
	/**
	 * ����ʾ16����ֵ���ַ���ת��Ϊbyte���飬 ��public static String byteArrayToHexString(byte[] b)
	 * ��Ϊ�����ת������
	 * 
	 * @param strIn
	 *            ��Ҫת�����ַ���
	 * @return ת�����byte����
	 * @throws Exception
	 *             �������������κ��쳣�������쳣ȫ���׳�
	 * @author LiGuoQing
	 */
	public static byte[] hexString2ByteArray(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// �����ַ���ʾһ���ֽڣ������ֽ����鳤�����ַ������ȳ���2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * ����3DES��Կ.
	 * 
	 * @param key_byte
	 *            seed key
	 * @throws Exception
	 * @return javax.crypto.SecretKey Generated DES key
	 */
	public static javax.crypto.SecretKey genDESKey(byte[] key_byte)
			throws Exception {

		SecretKey k = null;
		k = new SecretKeySpec(key_byte, "DESede");
		return k;
	}

	public static javax.crypto.SecretKey genDESKey() throws Exception {
		String keyStr = "$1#2@f3&4~6%7!a+*cd(e-h)";// ʹ�ù̶�key
		// System.out.println("DES ����ʹ�õĹ̶�key��keyStr ���� " + keyStr);
		byte key_byte[] = keyStr.getBytes();// 3DES 24 bytes key
		SecretKey k = null;
		k = new SecretKeySpec(key_byte, "DESede");
		return k;
	}

	public static javax.crypto.SecretKey genDESKey(String key) throws Exception {
		String keyStr = key;// ʹ�ù̶�key
		// System.out.println("DES ����ʹ�õĹ̶�key��keyStr ���� " + keyStr);
		byte key_byte[] = keyStr.getBytes();// 3DES 24 bytes key
		SecretKey k = null;
		k = new SecretKeySpec(key_byte, "DESede");
		return k;
	}

	/**
	 * 3DES ����(byte[]).
	 * 
	 * @param key
	 *            SecretKey
	 * @param crypt
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] desDecrypt(javax.crypto.SecretKey key, byte[] crypt)
			throws Exception {
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
		cipher.init(javax.crypto.Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(crypt);
	}

	/**
	 * 3DES ����(String).
	 * 
	 * @param key
	 *            SecretKey
	 * @param crypt
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desDecrypt(javax.crypto.SecretKey key, String crypt)
			throws Exception {
		//return byteArrayToHexString(desDecrypt(key, crypt.getBytes()));
		return new String(desDecrypt(key,hexString2ByteArray(crypt)));
	}
	
	/**
	 * 3DES����
	 * @param key ������Կ,��ԿҪ��24λ���������24λ����0������24λ����ȡǰ24λ
	 * @param crypt �������ַ���
	 * */
	public static String desDecrypt(String key,String crypt) {
		//��KEY�����24λ��������㣬�ں��油0�������������ȡǰ��λ
		String procKey = procKey(key);
		try {
			return CryptTool.desDecrypt(genDESKey(procKey), crypt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * ��KEY�����24λ��������㣬�ں��油0�������������ȡǰ��λ
	 * */
	private static String procKey(String key) {
		if(key.length()<24) {
			while(key.length()<24) {
				key = key + "0";
			}
			return key;
		}else if(key.length()>24) {
			return key.substring(0,24);
		}
		
		return key;
	}
	
	/**
	 * 3DES����(byte[]).
	 * 
	 * @param key
	 *            SecretKey
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static byte[] desEncrypt(javax.crypto.SecretKey key, byte[] src)
			throws Exception {
		javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("DESede");
		cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(src);
	}

	/**
	 * 3DES����(String).
	 * 
	 * @param key
	 *            SecretKey
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[]
	 */
	public static String desEncrypt(javax.crypto.SecretKey key, String src)
			throws Exception {
		return byteArrayToHexString(desEncrypt(key, src.getBytes()));
	}

	/**
	 * 3DES����
	 * @param key ������Կ,��ԿҪ��24λ���������24λ����0������24λ����ȡǰ24λ
	 * @param ��Ҫ�����ܵ��ַ���
	 * @return 3DES���ܣ�����ת����16�����ַ���
	 * */
	public static String desEncrypt(String key,String src) {
		String procKey = procKey(key);
		try {
			return desEncrypt(CryptTool.genDESKey(procKey),src);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * MD5 ժҪ����(byte[]).
	 * 
	 * @param src
	 *            byte[]
	 * @throws Exception
	 * @return byte[] 16 bit digest
	 */
	public static byte[] md5Digest(byte[] src) throws Exception {
		java.security.MessageDigest alg = java.security.MessageDigest
				.getInstance("MD5"); // MD5 is 16 bit message digest

		return alg.digest(src);
	}

	/**
	 * MD5 ժҪ����(String).
	 * 
	 * @param src
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String md5Digest(String src) throws Exception {
		return byteArrayToHexString(md5Digest(src.getBytes()));
	}

	/**
	 * BASE64 ����.
	 * 
	 * @param src
	 *            String inputed string
	 * @return String returned string
	 */
	public static String base64Encode(String src) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

		return encoder.encode(src.getBytes());
	}

	/**
	 * BASE64 ����(byte[]).
	 * 
	 * @param src
	 *            byte[] inputed string
	 * @return String returned string
	 */
	public static String base64Encode(byte[] src) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();

		return encoder.encode(src);
	}

	/**
	 * BASE64 ����.
	 * 
	 * @param src
	 *            String inputed string
	 * @return String returned string
	 */
	public static String base64Decode(String src) {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

		try {
			return byteArrayToHexString(decoder.decodeBuffer(src));
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * BASE64 ����(to byte[]).
	 * 
	 * @param src
	 *            String inputed string
	 * @return String returned string
	 */
	public static byte[] base64DecodeToBytes(String src) {
		sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();

		try {
			return decoder.decodeBuffer(src);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * �Ը����ַ����� URL ����.
	 * 
	 * @param src
	 *            String
	 * @return String
	 */
	public static String urlEncode(String src) {
		try {
			src = java.net.URLEncoder.encode(src, "UTF-8");

			return src;
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return src;
	}

	/**
	 * �Ը����ַ����� URL ����
	 * 
	 * @param value
	 *            ����ǰ���ַ���
	 * @return �������ַ���
	 */
	public String urlDecode(String value) {
		try {
			return java.net.URLDecoder.decode(value, "UTF-8");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	
	public static String generate24RandomString(){
		 String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
			     "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			     "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
			     "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			     "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			     "X", "Y", "Z" };
		   int LengthOfRandom = a.length;
		   Random random = new Random();

		   int tempInt = 0;
		   StringBuffer temp = new StringBuffer("");
		   for (int i = 0; i < 24; i++) {
		    tempInt = random.nextInt(LengthOfRandom);
		    temp.append(a[tempInt]);
		   }
		   String random24String=temp.toString();
		   return random24String;
		}
	
	
	/** Test crypt */
	public static void main(String[] args) {
		try {
//			// ��õ���������
//			String desStr = "MERCHANTID=0219999999&ORDERSEQ=20080225150029000001&ORDERDATE=20080225&ORDERAMOUNT=200";
//
//			// ʹ�ù̶�keyֵ
//			String keyStr = "123456";// ʹ�ù̶�key
//			desStr = desStr + "&KEY=" + keyStr;// ��keyֵ������������֯��һ�������ܵĴ�
//			System.out.println("�����ܵ��ַ��� desStr ���� " + desStr);
//			// ת���ֽ�����
//			byte src_byte[] = desStr.getBytes();
//
//			// MD5ժҪ
//			byte[] md5Str = md5Digest(src_byte);
//			// ��������SIGN
//			String SING = byteArrayToHexString(md5Str);
//			System.out.println("SING == " + SING);
			CryptTool tTool = new CryptTool();
			String keyStr = tTool.generate24RandomString();
			System.out.println(keyStr);
			SecretKey desKey = tTool.genDESKey(keyStr);
			String miwenString=	tTool.desEncrypt(desKey, "�ַ�������Ŀ");
			System.out.println(miwenString);
			String mingwen=	tTool.desDecrypt(desKey, miwenString);
			System.out.println(mingwen);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
