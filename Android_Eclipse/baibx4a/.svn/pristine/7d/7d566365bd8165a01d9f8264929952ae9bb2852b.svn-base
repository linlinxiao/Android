package com.baoxiao.util;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Encrypt {
	public static Boolean isValidKey(String data,String key){
		if (key == null || "".equals(key)) {return false;}
	    String date = new SimpleDateFormat("MM-dd",Locale.getDefault()).format(new Date());
	    String key1 = "baoxiao.com";
	    String da = data + key1 + date;
	    String md5 = string2MD5(da);
	    if (md5.equals(key)) {
			return true;
		}
		return false;
	}
	
    /*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    }  
//	
//	 public static String stringMD5(String pw) {
//		 try { 
//			 MessageDigest messageDigest = MessageDigest.getInstance("MD5"); 
//			 byte[] inputByteArray = pw.getBytes(); 
//			 messageDigest.update(inputByteArray); 
//			 byte[] resultByteArray = messageDigest.digest(); 
//			 return byteArrayToHex(resultByteArray); 
//		 } catch (NoSuchAlgorithmException e) { 
//			 return null; 
//		 } 
//	}
//
//	public static String byteArrayToHex(byte[] byteArray) { 
//        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' }; 
//        char[] resultCharArray =new char[byteArray.length * 2]; 
//        int index = 0;
//        for (byte b : byteArray) { 
//           resultCharArray[index++] = hexDigits[b>>> 4 & 0xf]; 
//           resultCharArray[index++] = hexDigits[b& 0xf]; 
//        }
//        return new String(resultCharArray); 
//    }
}
