package io.hedwig.tcexecutor.support.performance.service;

import io.hedwig.tcexecutor.support.env.entity.PerfMachine;
import io.hedwig.tcexecutor.support.env.repository.PerfMachineRepository;
import io.hedwig.tcexecutor.support.jmeter.JMeterCommandTemplate;
import io.hedwig.tcexecutor.support.performance.PerfJobEnum;
import io.hedwig.tcexecutor.support.performance.entity.PerfJob;
import io.hedwig.tcexecutor.support.ssh.HostConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerfJobService {

    @Autowired
    private PerfMachineRepository perfMachineRepository;

    public void save(PerfJob job){
        System.out.println("Not Implemented!!");
    }


    public boolean run(PerfJob job){

        if(job.getJobStatus()== PerfJobEnum.STANDBY.getJobStatus()){
            String runMachines = job.getRunMachines();
            String[] machineIds = runMachines.split(",");
            for (String machineId : machineIds) {
                //get machine detail, and build jmeter run script
                PerfMachine pm = perfMachineRepository.getOne(Long.parseLong(machineId));
                HostConfig hostConfig = HostConfig.builder().ip(pm.getIp())
                        .port(Integer.parseInt(pm.getPort())).userName(pm.getUser())
                        .password(pm.getPwd()).build();
                JMeterCommandTemplate command = JMeterCommandTemplate.jmeter(hostConfig);
                command.startJMeter(job.getJmxFileLocation(),job.getLogFileLocation(),job.getResultLocation());
            }
            return true;
        }

        throw new RuntimeException("测试任务正在进行中.......,请等待......");

    }

    public void stop(PerfJob job){

        String runMachines = job.getRunMachines();
        String[] machineIds = runMachines.split(",");
        for (String machineId : machineIds) {
            //get machine detail, and build jmeter run script
            PerfMachine pm = perfMachineRepository.getOne(Long.parseLong(machineId));
            HostConfig hostConfig = HostConfig.builder().ip(pm.getIp())
                    .port(Integer.parseInt(pm.getPort())).userName(pm.getUser())
                    .password(pm.getPwd()).build();
            JMeterCommandTemplate command = JMeterCommandTemplate.jmeter(hostConfig);
            command.stopJMeter(job.getJmxFileLocation());
        }
    }

    public void getJobStatus(PerfJob job){
        System.out.println("get job status");
    }
}
