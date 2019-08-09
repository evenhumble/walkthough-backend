package io.qkits.kafkaservice.dto;

import lombok.Data;

@Data
public class ProduceDTO {
    private String bootStrapServer;
    private String topic;
    private String groupId;
    private Object data;

    public String getBootStrapServer() {
        return bootStrapServer;
    }

    public void setBootStrapServer(String bootStrapServer) {
        this.bootStrapServer = bootStrapServer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
