package com.cmos.ipg.utils;

import java.io.ByteArrayOutputStream;

/**
 * <code>字节转换工具类</code>
 * @author Hardy
 * @date 2016/4/29 11:40
 * @version 1.0
 */
public class ByteUtil {

    private static String hexString="0123456789ABCDEF"; /* * 将字符串编码成16进制数字,适用于所有字符（包括中文） */

    /**
     * <code>byte to integer</code>
     * @param byte0
     * @return
     */
    protected static int ByteToInt(byte byte0){
        return byte0;
    }

    /**
     * <code>byte to integer</code>
     * @param abyte0
     * @return
     */
    protected static int BytesToInt(byte abyte0[]){
        return ((0xff & abyte0[0]) << 8) + abyte0[1];
    }

    /**
     * <code>4 bytes to integer</code>
     * @param abyte0
     * @return
     */
    protected static int Bytes4ToInt(byte abyte0[]){
        return (0xff & abyte0[0]) << 24 | (0xff & abyte0[1]) << 16 |(0xff & abyte0[2]) << 8 | 0xff & abyte0[3];
    }

    /**
     * <code>8 bytes to long</code>
     * @param abyte0
     * @return
     */
    protected static long Bytes8ToLong(byte abyte0[])
    {
        long ret = 0;

        ret = (0xffL & abyte0[0]) << 56 | (0xffL & abyte0[1]) << 48 | (0xffL & abyte0[2]) << 40 | (0xffL & abyte0[3]) << 32 | (0xffL & abyte0[4]) << 24 | (0xffL & abyte0[5]) << 16 | (0xffL & abyte0[6]) << 8 | 0xffL & abyte0[7];

        return ret;
    }

    /**
     *
     * @param srcAbyte
     * @param destAbyte
     * @param srcFrom: srcindex
     * @param srcTo
     * @param destFrom
     */
    protected static void BytesCopy(byte srcAbyte[], byte destAbyte[], int srcFrom, int srcTo, int destFrom)
    {
        // check null
        if( srcAbyte == null || destAbyte == null  ) {
            return;
        }
        // copy
        int i1 = 0;
        for(int l = srcFrom; l <= srcTo; l++ )
        {
            if( destFrom + i1 >= destAbyte.length ) {
                break;
            }
            if( l >= srcAbyte.length ) {
                break;
            }
            destAbyte[destFrom + i1] = srcAbyte[l];
            i1++;
        }
    }

    /**
     *
     * @param i
     * @return
     */
    public static byte IntToByte(int i)
    {
        return (byte)i;
    }

    /**
     *
     * @param i
     * @return
     */
    protected static byte[] IntToBytes(int i)
    {
        byte abyte0[] = new byte[2];
        abyte0[1] = (byte)(0xff & i);
        abyte0[0] = (byte)((0xff00 & i) >> 8);
        return abyte0;
    }
    /**
     *
     * @param i
     * @param abyte0
     */
    protected static void IntToBytes(int i, byte abyte0[])
    {
        abyte0[1] = (byte)(0xff & i);
        abyte0[0] = (byte)((0xff00 & i) >> 8);
    }

    /**
     *
     * @param i
     * @return
     */
    protected static byte[] IntToBytes4(int i)
    {
        byte abyte0[] = new byte[4];
        abyte0[3] = (byte)(0xff & i);
        abyte0[2] = (byte)((0xff00 & i) >> 8);
        abyte0[1] = (byte)((0xff0000 & i) >> 16);
        abyte0[0] = (byte)((0xff000000 & i) >> 24);
        return abyte0;
    }

    /**
     *
     * @param i
     * @param abyte0
     */
    protected static void IntToBytes4(int i, byte abyte0[])
    {
        abyte0[3] = (byte)(0xff & i);
        abyte0[2] = (byte)((0xff00 & i) >> 8);
        abyte0[1] = (byte)((0xff0000 & i) >> 16);
        abyte0[0] = (byte)(int)((0xffffffffff000000L & (long)i) >> 24);
    }

    /**
     *
     * @param i
     * @return
     */
    public static byte[] LongToBytes8(long i)
    {
        byte abyte0[] = new byte[8];
        abyte0[7] = (byte)(0xffL & i);
        abyte0[6] = (byte)((0xff00L & i) >> 8);
        abyte0[5] = (byte)((0xff0000L & i) >> 16);
        abyte0[4] = (byte)((0xff000000L & i) >> 24);
        abyte0[3] = (byte)((0xff00000000L & i) >> 32);
        abyte0[2] = (byte)((0xff0000000000L & i) >> 40);
        abyte0[1] = (byte)((0xff000000000000L & i) >> 48);
        abyte0[0] = (byte)((0xff00000000000000L & i) >> 56);
        return abyte0;
    }

    /**
     * <code>字节数组转换成16进制字符串</code>
     * @param bytes
     * @return
     */
    public static String bytes2hex03(byte[] bytes)
    {
        final String HEX = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes)
        {
            // 取出这个字节的高4位，然后与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出这个字节的低位，与0x0f与运算，得到一个0-15之间的数据，通过HEX.charAt(0-15)即为16进制数
            sb.append(HEX.charAt(b & 0x0f));
        }

        return sb.toString();
    }

    /**
     * <code>16进制解码成字符串</code>
     * @param bytess
     * @return
     */
    public static String decode(String bytess)
    {
        String bytes = removeSpaceHex(bytess);
        ByteArrayOutputStream baos=new ByteArrayOutputStream(bytes.length()/2);
        // 将每2位16进制整数组装成一个字节
        for(int i=0;i<bytes.length();i+=2)
            baos.write((hexString.indexOf(bytes.charAt(i))<<4 |hexString.indexOf(bytes.charAt(i+1))));
        return new String(baos.toByteArray());
    }

    /**
     * <code>将字符串编码成16进制</code>
     * @param str
     * @return
     */
    public static String encode(String str)
    {
        // 根据默认编码获取字节数组
        byte[] bytes=str.getBytes();
        StringBuilder sb=new StringBuilder(bytes.length*2);
        // 将字节数组中每个字节拆解成2位16进制整数
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
        }
        return sb.toString();
    }

    /**
     * <code>将16进制字符串去掉空格</code>
     * @param str
     * @return
     */
    public static  String removeSpaceHex(String str){
        //去掉16进制字符串含有的空格
        String re="";
        re = str.replaceAll("\\s*", "");
        return re;
    }
}
