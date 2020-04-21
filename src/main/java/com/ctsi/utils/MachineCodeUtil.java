package com.ctsi.utils;

import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * ClassName MachineCodeUtil
 * Description //TODO
 * Author tongliwei
 * Date 2020/2/6 11:41
 * Version v1.0
 **/
public class MachineCodeUtil {
    private static String getMac() {
        try {
            Enumeration<NetworkInterface> el = NetworkInterface.getNetworkInterfaces();
            while (el.hasMoreElements()) {
                byte[] mac = el.nextElement().getHardwareAddress();
                if (mac == null)
                    continue;
                String hexstr = SerialUtil.bytesToHexString(mac);
                return hexstr;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getMachineCode() {
        Set<String> result = new HashSet<>();
        String mac = getMac();
        result.add(mac);
        Properties props = System.getProperties();
        String javaVersion = props.getProperty("java.version");
        result.add(javaVersion);
        String javaVMVersion = props.getProperty("java.vm.version");
        result.add(javaVMVersion);
        String osVersion = props.getProperty("os.version");
        result.add(osVersion);
        String code = getMD5(result.toString());
        return code;

    }

    public static String getMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
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
        for (int i = 0; i < md5Bytes.length; i++) {

            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
