package com.crc.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class SHA256 {

  /** @param args */
  public static void main(String[] args) {
    //    Map<String, String> result = new HashMap<String, String>();
    //    System.out.println("------------Start---------------");
    //    result.put("LOGIN", "ZENGAIHUA");
    //    result.put("PASSWD", "123456");
    //    System.out.println(toXML(result));
    //    System.out.println("------------End---------------");
    System.out.println(sha256("oa-001"));
  }

  public static String sha256(final String contents) {
    String encryptedContents = "";
    try {
      MessageDigest messageDigest = null;
      messageDigest = MessageDigest.getInstance("SHA-256");
      messageDigest.reset();
      messageDigest.update(contents.getBytes("UTF-8"));
      encryptedContents = byteToString(messageDigest.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      System.exit(-1);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }

    return encryptedContents;
  }

  private static String byteToString(byte[] bByte) {
    StringBuffer encrytStrBuf = new StringBuffer();

    for (int i = 0; i < bByte.length; i++) {
      if (Integer.toHexString(0xFF & bByte[i]).length() == 1) {
        encrytStrBuf.append("0").append(Integer.toHexString(0xFF & bByte[i]));
      } else {
        encrytStrBuf.append(Integer.toHexString(0xFF & bByte[i]));
      }
    }
    return encrytStrBuf.toString().toUpperCase();
  }

  public static String toXML(Map<String, String> map) {
    StringBuffer sb = new StringBuffer();
    String login = map.get("LOGIN");
    for (Map.Entry<String, String> entry : map.entrySet()) {
      String value = (String) entry.getValue();
      if ("PASSWD".equals(entry.getKey())) {
        value = SHA256.sha256(value);
        value = SHA256.sha256("CRCLDAP+" + login + "+" + value);
      }
      sb.append("<" + (String) entry.getKey() + ">")
          .append(value)
          .append("</" + (String) entry.getKey() + ">");
    }
    return sb.toString();
  }
}
