package io.qkits.toolsets;


import cn.hutool.extra.ftp.Ftp;

/**
 * ToDo
 * 1. cd folder 2. upload file
 * 3. download file 4. multiple files upload and download
 * 5. folder to folder
 */
public class QFtpClient {

  private String serverIp;
  private int serverPort;
  private String loginName;
  private String password;
  private Ftp ftpClient;

  public Ftp getFtpClient() {
    return ftpClient;
  }



} 
