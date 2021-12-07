package com.fangming.rabbitmqdemo.ftp;

/**
 * @author Ming
 * @date 2021/10/29 10:00
 */

import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPTransferType;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Ftp 工具类
 */
public class FtpHelper {


    private FTPClient ftp;


    public FtpHelper() {


    }

    /**
     * 初始化Ftp信息
     *
     * @param ftpServer   ftp服务器地址
     * @param ftpPort     Ftp端口号
     * @param ftpUsername ftp 用户名
     * @param ftpPassword ftp 密码
     */
    public FtpHelper(String ftpServer, int ftpPort, String ftpUsername,
                     String ftpPassword) {
        connect(ftpServer, ftpPort, ftpUsername, ftpPassword);
    }

    /**
     * 连接到ftp
     *
     * @param ftpServer   ftp服务器地址
     * @param ftpPort     Ftp端口号
     * @param ftpUsername ftp 用户名
     * @param ftpPassword ftp 密码
     */
    public void connect(String ftpServer, int ftpPort, String ftpUsername, String ftpPassword) {
        ftp = new FTPClient();
        try {
            ftp.setControlEncoding("UTF-8");
            ftp.setRemoteHost(ftpServer);
            ftp.setRemotePort(ftpPort);
            ftp.setTimeout(6000);
            ftp.setConnectMode(FTPConnectMode.PASV);
            ftp.connect();
            ftp.login(ftpUsername,ftpPassword);
            ftp.setType(FTPTransferType.BINARY);
            System.out.println("成功");
        } catch (Exception e) {
            e.printStackTrace();
            ftp = null;
        }
    }

    /**
     * 更改ftp路径
     *
     * @param ftp
     * @param dirName
     * @return
     */
    public boolean checkDirectory(FTPClient ftp, String dirName) {
        boolean flag;
        try {
            ftp.chdir(dirName);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 断开ftp链接
     */
    public void disconnect() {
        try {
            if (ftp.connected()) {
                ftp.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取ftp文件流
     *
     * @param filePath ftp文件路径
     * @return s
     * @throws Exception
     */
    public InputStream downloadFile(String filePath) throws Exception {
        InputStream inputStream = null;
        String fileName = "";
        filePath = StringUtils.removeStart(filePath, "/");
        int len = filePath.lastIndexOf("/");
        if (len == -1) {
            if (filePath.length() > 0) {
                fileName = filePath;
            } else {
                throw new Exception("没有输入文件路径");
            }
        } else {
            fileName = filePath.substring(len + 1);

            String type = filePath.substring(0, len);
            String[] typeArray = type.split("/");
            for (String s : typeArray) {
                ftp.chdir(s);
            }
        }
        byte[] data;
        try {
            data = ftp.get(fileName);
            inputStream = new ByteArrayInputStream(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 上传文件到ftp
     *
     * @param file     文件对象
     * @param filePath 上传的路径
     * @throws Exception
     */
    public void uploadFile(File file, String filePath) throws Exception {
        InputStream inStream = new FileInputStream(file);
        uploadFile(inStream, filePath);
    }

    /**
     * 上传文件到ftp
     *
     * @param inStream 上传的文件流
     * @param filePath 上传路径
     * @throws Exception
     */
    public void uploadFile(InputStream inStream, String filePath)
            throws Exception {
        if (inStream == null) {
            return;
        }
        String fileName = "";
        filePath = StringUtils.removeStart(filePath, "/");
        int len = filePath.lastIndexOf("/");
        if (len == -1) {
            if (filePath.length() > 0) {
                fileName = filePath;
            } else {
                throw new Exception("没有输入文件路径");
            }
        } else {
            fileName = filePath.substring(len + 1);
            String type = filePath.substring(0, len);
            String[] typeArray = type.split("/");
            for (String s : typeArray) {
                if (!checkDirectory(ftp, s)) {
                    ftp.mkdir(s);
                }
            }
        }
        ftp.put(inStream, fileName);
    }

    /**
     * 删除ftp文件
     *
     * @param filePath 文件路径
     * @throws Exception
     */
    public void deleteFile(String filePath) throws Exception {
        String fileName = "";
        filePath = StringUtils.removeStart(filePath, "/");
        int len = filePath.lastIndexOf("/");
        if (len == -1) {
            if (filePath.length() > 0) {
                fileName = filePath;
            } else {
                throw new Exception("没有输入文件路径");
            }
        } else {
            fileName = filePath.substring(len + 1);

            String type = filePath.substring(0, len);
            String[] typeArray = type.split("/");
            for (String s : typeArray) {
                if (checkDirectory(ftp, s)) {
                    ftp.chdir(s);
                }
            }
        }
        ftp.delete(fileName);
    }

    /**
     * 切换目录
     *
     * @param path
     * @throws Exception
     */
    public void changeDirectory(String path) {
        if (!StringUtils.isEmpty(path)) {
            try {
                ftp.chdir(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

