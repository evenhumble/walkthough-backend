package io.hedwig.tcexecutor.support.ssh;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

public class SSHTemplate {

    private HostConfig hostConfig;
    private Connection sshConnection;

    public SSHTemplate(HostConfig config){
        try {
            initConnection(config);
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException("setup Session failed:"+config.getIp());
        }
    }

    private void initConnection(HostConfig hostConfig)
            throws IOException {
        this.hostConfig=hostConfig;
        this.sshConnection = new Connection(hostConfig.getIp(),hostConfig.getPort());
        this.sshConnection.connect();
        boolean isAuthenticated = this.sshConnection.authenticateWithPassword(hostConfig.getUserName(),hostConfig.getPassword());
        if(!isAuthenticated){
            throw new RuntimeException("login failed! please check it");
        }
    }

    public SSHResponse execCommand(String command){
       return execCommands(Lists.newArrayList(command));
    }

    //todo performance issue
    private List<SSHResponse> exec(List<String> commands) throws IOException {
        Session session = null;
        List<SSHResponse> result = Lists.newArrayList();
        for (String command : commands) {
           try {
               session = this.sshConnection.openSession();
               session.execCommand(command);
               session.waitForCondition(64,3000);
               SSHResponse response = new SSHResponse();
               response.setExitCode(session.getExitStatus());
//               response.setStdout(getOutputAsString(session.getStdout()));
//               response.setStderr(getOutputAsString(session.getStderr()));
               result.add(response);
               session.close();
           }finally {
               assert session != null;
               session.close();
           }
        }
        return result;
    }

    public SSHResponse execCommands(List<String> commands){

        try {
            List<SSHResponse> response = exec(commands);
            return response.size()>=1?response.get(response.size()-1):SSHResponse.error();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SSHResponse.error();
    }

    public void scp(String sourceFilePath,String targetPath){
        try {
            SCPClient scpClient = this.sshConnection.createSCPClient();
            scpClient.put(sourceFilePath,targetPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //

    public void close(){
        this.sshConnection.close();
    }

    private String getOutputAsString(InputStream input){

        if(input==null) return "";
        InputStream in = new StreamGobbler(input);
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(
                        new InputStreamReader(in,"utf-8"));
            String line = null;
            while ((line = br.readLine())!=null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
