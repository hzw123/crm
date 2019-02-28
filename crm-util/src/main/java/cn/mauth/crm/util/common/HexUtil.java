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

    public static String encode(String data){
        return new String(encodeHex(data.getBytes(),DIGITS_LOWER));
    }

    public static String decode(String data){

        char[] datas=data.toCharArray();

        byte[] bytes=new byte[data.length()/2];

        int j=0;
        for (int i=0;i<datas.length;i=i+2){

            int n=(0x0f & contion(datas[i],DIGITS_LOWER)) <<4;

            int m=(0x0f & contion(datas[i+1],DIGITS_LOWER));

            bytes[j]=(byte) (n+m);

            j++;
        }
        return new String(bytes);
    }

    private static int contion(char t,char[] data){
        for (int i=0;i<data.length;i++){
            if(data[i]==t)
                return i;
        }
        return -1;
    }


    public static void main(String[] args){
        System.err.println(decode("2f63726d2f4a50472f6e6443615867436e42444a536d5a7a425f62672e4a5047"));
    }
}
