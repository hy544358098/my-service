package com.x.common.exception;


import com.x.common.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

@Getter
@Slf4j
public class ServiceException extends RuntimeException {

    private String retMsg;

    private String retCode;


    public <T> ServiceException(Enum enumObject) {
        this.retCode = ((ErrorCodeEnum) enumObject).getRetCode();
        this.retMsg = ((ErrorCodeEnum) enumObject).getRetMsg();
    }

    public ServiceException(String code, String message) {
        this.retCode = code;
        this.retMsg = message;
    }

    public ServiceException(String message) {
        this.retCode = "error";
        this.retMsg = message;
    }
}
