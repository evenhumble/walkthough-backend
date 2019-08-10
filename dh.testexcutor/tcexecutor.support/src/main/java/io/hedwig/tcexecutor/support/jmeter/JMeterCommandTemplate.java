package io.hedwig.tcexecutor.support.jmeter;

import io.hedwig.tcexecutor.support.ssh.HostConfig;
import io.hedwig.tcexecutor.support.ssh.SSHCommands;
import io.hedwig.tcexecutor.support.ssh.SSHResponse;
import io.hedwig.tcexecutor.support.ssh.SSHTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

public class JMeterCommandTemplate  {
    private static final Logger logger = LoggerFactory.getLogger(JMeterCommandTemplate.class);
    private SSHTemplate sshTemplate;
    private static final String JMETER_ENV_FILE ="jmeter_env.sh";


    public JMeterCommandTemplate(HostConfig config) {
        sshTemplate= new SSHTemplate(config);
    }

    public static JMeterCommandTemplate jmeter(HostConfig hostConfig){
        return new JMeterCommandTemplate(hostConfig);
    }

    public SSHResponse startJMeter(String jmxFile,String logFile,String resultLocation){

        String jmeterCommand = SSHCommands.buildJMeterCommand(jmxFile,logFile,resultLocation);
        logger.info("run jmeter script: "+jmeterCommand);
        return this.sshTemplate.execCommand("cd /www && "+jmeterCommand);
    }

    public SSHResponse stopJMeter(String jmxFile){
        String stopJmeterScript = SSHCommands.buildKillJMeterCommand(jmxFile);
        return this.sshTemplate.execCommand(stopJmeterScript);
    }

    public void setupJMETEREnv(){
        String jmxFile = ClassLoader.getSystemClassLoader().getResource(JMETER_ENV_FILE)
                .getPath();
        sshTemplate.scp(jmxFile,"/www");
    }
}
