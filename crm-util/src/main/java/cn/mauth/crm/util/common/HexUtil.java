package cn.mauth.crm.util.common;

import java.security.MessageDigest;

public class HexUtil {

    private static final char[] DIGITS_LOWER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String md5Hex(String data){
        byte[] result=null;
        try {
            MessageDigest md5=MessageDigest.getInstance("MD5");
            result=md5.digest(data.getBytes("utf-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return encodeHexStr(result,true);
    }

    private static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }


    private static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    private static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;
        for(int var5 = 0; i < l; ++i) {
            out[var5++] = toDigits[(0xff & data[i]) >>> 4];
            out[var5++] = toDigits[0x0f & data[i]];
        }

        return out;
    }

}
