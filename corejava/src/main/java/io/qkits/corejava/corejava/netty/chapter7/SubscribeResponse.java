package io.qkits.corejava.corejava.netty.chapter7;

import java.io.Serializable;

/**
 * @author mazhiqiang
 */
public class SubscribeResponse implements Serializable {

    private int subRequestId;
    private int responseCode;
    private String description;

    public int getSubRequestId() {
        return subRequestId;
    }

    public void setSubRequestId(int subRequestId) {
        this.subRequestId = subRequestId;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubscribeResponse{" +
                "subRequestId=" + subRequestId +
                ", responseCode=" + responseCode +
                ", description='" + description + '\'' +
                '}';
    }
}
