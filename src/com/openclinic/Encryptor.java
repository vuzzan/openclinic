package com.openclinic;

public class Encryptor
{
    //private static final String m_strPrivateKey = "64p0L@r1$";
    public Encryptor()
    {
    }

    public static String encrypt(String pass)
    {
        String strTarget = XORString(pass);
        strTarget = StringToHex(strTarget);
        return strTarget;
    }

    public static String decrypt(String pass)
    {
        String strTarget = HexToString(pass);
        strTarget = XORString(strTarget);
        return strTarget;
    }

    private static String GetKeyForLength(int nLength)
    {
        int nKeyLen = "64p0L@r1$".length();
        int nRepeats = nLength / nKeyLen + 1;
        String strResult = "";
        for(int i = 0; i < nRepeats; i++)
        {
            strResult = strResult + "4p0L4p0L@r14p0L$";
        }
        return strResult.substring(0, nLength);
    }

    private static String HexToString(String str)
    {
        //StringBuffer sb = new StringBuffer();
        char buffDigit[] = new char[4];
        buffDigit[0] = '0';
        buffDigit[1] = 'x';
        int length = str.length() / 2;
        byte bytes[] = new byte[length];
        for(int i = 0; i < length; i++)
        {
            buffDigit[2] = str.charAt(i * 2);
            buffDigit[3] = str.charAt(i * 2 + 1);
            Integer b = Integer.decode(new String(buffDigit));
            bytes[i] = (byte)b.intValue();
        }

        return new String(bytes);
    }

    private static String XORString(String strTarget)
    {
        int nTargetLen = strTarget.length();
        String strPaddedKey = GetKeyForLength(nTargetLen);
        //String strResult = "";
        byte bytes[] = new byte[nTargetLen];
        for(int i = 0; i < nTargetLen; i++)
        {
            int b = strTarget.charAt(i) ^ strPaddedKey.charAt(i);
            bytes[i] = (byte)b;
        }

        String result = new String(bytes);
        return result;
    }

    private static String StringToHex(String strInput)
    {
        StringBuffer hex = new StringBuffer();
        int nLen = strInput.length();
        for(int i = 0; i < nLen; i++)
        {
            char ch = strInput.charAt(i);
            int b = ch;
            String hexStr = Integer.toHexString(b);
            if(hexStr.length() == 1)
            {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b));
        }

        return hex.toString();
    }
}