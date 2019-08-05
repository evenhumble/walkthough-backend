package io.hedwig.dh.fileservice.web.dto;

import lombok.Data;

@Data
public class DHResponse {

    private String msg;
    private Integer code;
    private Object data;


    public DHResponse msg(String msg){
        this.msg = msg;
        return this;
    }
    public DHResponse data(Object data) {
        this.data=data;
        return this;
    }

    public DHResponse code(Integer code){
        this.code = code;
        return this;
    }

    public static DHResponse SUCCESS_RESP(Object data){
        DHResponse dhResponse = new DHResponse();
        dhResponse.data=data;
        dhResponse.msg("OK");
        dhResponse.code(0);
        return dhResponse;
    }

    public static DHResponse FAIL(Object data){
        DHResponse dhResponse = new DHResponse();
        dhResponse.data=data;
        dhResponse.msg("FAIL");
        dhResponse.code(1); //upload file failed
        return dhResponse;
    }

    public static DHResponse FAIL(){
        DHResponse dhResponse = new DHResponse();
        dhResponse.msg("FAIL");
        dhResponse.code(1); //upload file failed
        return dhResponse;
    }
}
