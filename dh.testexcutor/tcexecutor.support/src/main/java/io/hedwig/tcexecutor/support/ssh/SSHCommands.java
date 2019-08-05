package io.hedwig.tcexecutor.support.ssh;

public class SSHCommands {
    public static String ECHO_HELLOWORLD="echo 'hello world'";
    public static String SCP_COMMAND="";

    public static String JMETER_COMMAND_TPL ="nohup jmeter -n -t %s -l %s -e -o %s &";
    public static String KILL_JMETER_TPL = "ps aux | grep jmeter | grep %s | grep -v 'grep' | xargs kill -9";

    public static String buildJMeterCommand(String jmxFile,String logLocation,String reportLocation){
        return String.format(JMETER_COMMAND_TPL,jmxFile,logLocation,reportLocation);
    }

    public static String buildKillJMeterCommand(String jmxFile){
        return String.format(KILL_JMETER_TPL,jmxFile);
    }

}
