package com.fangming.rabbitmqdemo.ftp;

import java.io.File;
import java.io.InputStream;

/**
 * @author Ming
 * @date 2021/10/29 10:02
 */
public class FtpTest {
    public static void main(String[] args) {
        try {
            // 从ftp下载文件
            FtpHelper ftp = new FtpHelper("192.168.5.187", 2121,"admin","admin");
 /*           File file = new File("D:\\ftpText.txt");
            ftp.uploadFile(file, "demo");
            ftp.disconnect();*/
            InputStream inputStream = ftp.downloadFile("/ftpTest/demo/testaaa.txt");
            System.out.println(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
