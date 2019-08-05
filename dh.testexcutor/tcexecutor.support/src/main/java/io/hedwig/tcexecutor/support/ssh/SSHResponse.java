package io.hedwig.tcexecutor.support.ssh;

public class SSHResponse {

    private String stdout;
    private String stderr;
    private Integer exitCode;



    public String getStdout() {
        return stdout;
    }

    public void setStdout(String stdout) {
        this.stdout = stdout;
    }

    public String getStderr() {
        return stderr;
    }

    public void setStderr(String stderr) {
        this.stderr = stderr;
    }


    public Integer getExitCode() {
        return exitCode;
    }

    public void setExitCode(Integer exitCode) {
        this.exitCode = exitCode;
    }


    public static SSHResponse error(){
        SSHResponse response = new SSHResponse();
        response.setExitCode(-1);
        return response;
    }
}
