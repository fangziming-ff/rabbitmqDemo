package com.fangming.rabbitmqdemo.pwd;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author Ming
 * @date 2021/10/27 11:55
 */
public class Asc {

        public static void main(String[] args) {
                String cont = "buykop";
                String key = "Yw/Vz4kpJUv0+E/4/LuZEA==";
                System.out.println("aes key = " + key);
                AES aes = SecureUtil.aes(Base64.decode(key));
                byte[] encrypt = aes.encrypt(cont);
                String enc = Base64.encode(encrypt);
                System.out.println("密文:" + enc);
                byte[] decrypt = aes.decrypt(enc);
                System.out.println(new String(decrypt));

                String str = SecureUtil.md5(cont);
                System.out.println("MD5: " + str);
                System.out.println("MD5 16位: " + DigestUtil.md5HexTo16(str));
                System.out.println("SHA-256:" + DigestUtil.sha256Hex("Admin@888" + "sda"));
        }




}
