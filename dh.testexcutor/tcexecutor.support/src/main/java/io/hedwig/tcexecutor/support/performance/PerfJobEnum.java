package io.hedwig.tcexecutor.support.performance;

public enum  PerfJobEnum {

    COMPLETE(1),RUNNING(2),STOPPED(3),STANDBY(4);
    private  int jobStatus;

    PerfJobEnum(int jobStatus) {
        this.jobStatus =jobStatus;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }
}
