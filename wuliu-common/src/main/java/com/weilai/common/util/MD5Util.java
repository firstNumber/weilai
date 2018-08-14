package com.weilai.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class MD5Util {
	private static final Logger logger = Logger.getLogger(MD5Util.class);

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	/**
	 * 转换byte到16进制
	 * 
	 * @param b
	 *            要转换的byte
	 * @return 16进制格式
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * MD5编码
	 * 
	 * @param origin
	 *            原始字符串
	 * @return 经过MD5加密之后的结果
	 */
	public static String encode(String origin) {
		return encode(origin, "");
	}

	/**
	 * MD5编码
	 * 
	 * @param origin
	 *            原始字符串
	 * @param charsetname
	 *            编码方式
	 * @return 经过MD5加密之后的结果
	 */
	public static String encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	/**
	 * MD5编码,返回byte数组
	 * 
	 * @param origin
	 * @param charsetname
	 * @return
	 * @Author: wangxingfei
	 * @Date: 2016年11月15日
	 */
	public static byte[] encodeByteArr(String origin, String charsetname) {
		if (StringUtils.isBlank(origin))
			return null;
		if (StringUtils.isBlank(charsetname))
			charsetname = "UTF-8";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(origin.getBytes(charsetname));
			byte[] arrByte = messageDigest.digest();
			return arrByte;
		} catch (Exception e) {
			logger.error("加密出错", e);
			return null;
		}
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	public static void main(String[] args) {
		System.out.println(encode("1234567890"));
	}

	/**
	 * 初始化密码
	 * 
	 * @return
	 * @Author: wangxingfei
	 * @Date: 2016年5月21日
	 */
	public static String initPwd() {
		return encode(encode("1234569161"));
	}

	// 获得MD5摘要算法的 MessageDigest 对象
	private static MessageDigest _mdInst = null;
	private static char _hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	private static MessageDigest getMdInst() {
		if (_mdInst == null) {
			try {
				_mdInst = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return _mdInst;
	}

	public final static String encodeCapitals(String s) {
		try {
			byte[] btInput = s.getBytes();
			// 使用指定的字节更新摘要
			getMdInst().update(btInput);
			// 获得密文
			byte[] md = getMdInst().digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = _hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = _hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
