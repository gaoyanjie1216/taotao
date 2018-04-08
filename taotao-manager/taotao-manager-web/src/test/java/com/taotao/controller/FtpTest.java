package com.taotao.controller;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;
import utils.FtpUtil;

import java.io.File;
import java.io.FileInputStream;

/**
 * TestFtpClient()测试图片上传工具类
 * Created by Gao on 2018/2/4.
 */
public class FtpTest {

    @Test
    public void TestFtpClient() throws Exception {
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接，默认是21端口
        ftpClient.connect("192.168.25.133", 21);
        //登录ftp服务器，使用用户名和密码
        ftpClient.login("ftpuser", "ftpuser");
        //上传文件，读取本地文件
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Gao\\Documents\\美图图库\\IMG_20150301_130210_副本.jpg"));

        //设置上传的路径
        ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
        //修改上传文件的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //第一个参数：服务器端文档名
        //第二个参数：上传文档的inputStream
        ftpClient.storeFile("hello1.jpg", inputStream);
        //关闭连接
        ftpClient.logout();
    }

    @Test
    public void testFtpUtil() throws Exception {
        FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\Gao\\Documents\\美图图库\\IMG_20150301_130210_副本.jpg"));
        FtpUtil.uploadFile("192.168.25.133", 21, "ftpuser", "ftpuser", "/home/ftpuser/www/images",
                "/2015/09/04", "hello.jpg", inputStream);
    }

}
