/******************************************************************************
 * Copyright (C) HeFei Royalstar Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为合肥荣事达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 *****************************************************************************/

package life.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author xuqingqing
 * @since 1.0
 * @version 2015年10月15日 xuqingqing
 */
public class MD5 {
    
    public static String getMD5String(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            str = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }
    
    public static void main(String[] args) {
        
        // e10adc3949ba59abbe56e057f20f883e
        System.out.println(MD5.getMD5String("123456"));
        
        // ea48576f30be1669971699c09ad05c94
        System.out.println(MD5.getMD5String("123456123456"));
        
//        System.out.println(URLEncoder.encode("userid=123&passwd=admin123++-="));
//        System.out.println(URLEncoder.encode("userid=123&passwd=admin123"));
    }
}
