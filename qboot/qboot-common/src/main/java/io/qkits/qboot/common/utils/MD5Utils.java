package io.qkits.qboot.common.utils;


import cn.hutool.crypto.digest.MD5;

/**
 * @author: patrick on 2019-08-30
 * @Description:
 */
public class MD5Utils {

  public static String byteArrayToHexString(byte b[]) {
    return new MD5().digestHex(b);
  }

  public static String MD5Encoding(String origin,String charset){
    return "";
  }
//  public static String byteArrayToHexString(byte b[]) {
//    StringBuffer resultSb = new StringBuffer();
//    for (int i = 0; i < b.length; i++) {
//      resultSb.append(byteToHexString(b[i]));
//    }
//    return resultSb.toString();
//  }
}
