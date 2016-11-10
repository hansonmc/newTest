package com.jlc.api.utils;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;

public class Base64 {
	private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
			.toCharArray();

	private static byte[] codes = new byte[256];

	static public char[] encode(byte[] data) {
		char[] out = new char[((data.length + 2) / 3) * 4];
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			boolean quad = false;
			boolean trip = false;
			int val = (0xFF & data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;
	}

	public static byte[] decode(char[] data) {
		int tempLen = data.length;
		for (int ix = 0; ix < data.length; ix++) {
			if ((data[ix] > 'ÿ') || (codes[data[ix]] < 0)) {
				tempLen--;
			}

		}

		int len = tempLen / 4 * 3;
		if (tempLen % 4 == 3) {
			len += 2;
		}
		if (tempLen % 4 == 2) {
			len++;
		}

		byte[] out = new byte[len];

		int shift = 0;
		int accum = 0;
		int index = 0;

		for (int ix = 0; ix < data.length; ix++) {
			int value = data[ix] > 'ÿ' ? -1 : codes[data[ix]];

			if (value >= 0) {
				accum <<= 6;
				shift += 6;
				accum |= value;
				if (shift >= 8) {
					shift -= 8;
					out[(index++)] = (byte) (accum >> shift & 0xFF);
				}

			}

		}

		if (index != out.length) {
			throw new Error("Miscalculated data length (wrote " + index
					+ " instead of " + out.length + ")");
		}

		return out;
	}

	static {
		for (int i = 0; i < 256; i++) {
			codes[i] = -1;
		}
		for (int i = 65; i <= 90; i++) {
			codes[i] = (byte) (i - 65);
		}
		for (int i = 97; i <= 122; i++) {
			codes[i] = (byte) (26 + i - 97);
		}
		for (int i = 48; i <= 57; i++) {
			codes[i] = (byte) (52 + i - 48);
		}
		codes[43] = 62;
		codes[47] = 63;
	}
	
	 public static byte[] getBytesBASE64(String s)
	    {
	        if (s == null)
	            return null;
	        BASE64Decoder decoder = new BASE64Decoder();
	        try
	        {
	            byte[] b = decoder.decodeBuffer(s);
	            return b;
	        } catch (Exception e)
	        {
	            return null;
	        }
	    }
	 
	 public static String getBASE64(String s)
	    {
	        if (s == null)
	            return null;
	        try
	        {
	            return (new sun.misc.BASE64Encoder()).encode(s.getBytes("UTF-8"));
	        } catch (UnsupportedEncodingException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	    }

	    public static String getBASE64(byte[] b)
	    {
	        return (new sun.misc.BASE64Encoder()).encode(b);
	    }

	    // �?BASE64 编码的字符串 s 进行解码
	    public static String getFromBASE64(String s)
	    {
	        if (s == null)
	            return null;
	        BASE64Decoder decoder = new BASE64Decoder();
	        try
	        {
	            byte[] b = decoder.decodeBuffer(s);
	            return new String(b, "UTF-8");
	        } catch (Exception e)
	        {
	            return null;
	        }
	    }
}
