package io.qkits.corejava.corejava.netty.chapter7;

import java.io.Serializable;

/**
 * @author mazhiqiang
 */
public class SubscribeRequest implements Serializable {

    private int subRequestId;
    private String userName;
    private String productName;
    private String phoneName;
    private String phoneNumber;

    public int getSubRequestId() {
        return subRequestId;
    }

    public void setSubRequestId(int subRequestId) {
        this.subRequestId = subRequestId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "SubscribeRequest{" +
                "subRequestId=" + subRequestId +
                ", userName='" + userName + '\'' +
                ", productName='" + productName + '\'' +
                ", phoneName='" + phoneName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
