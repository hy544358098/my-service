package com.crc.common.exception;


import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.utils.ResultVoUtil;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Description: 全局异常处理
 * Author: liuyw
 * CreateDate: 2019/4/10 11:43
 * Version: v1.0
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public String globalException(ServiceException e) {
        log.error("{retCode:{},retMsg:{}}", e.getRetCode(), e.getRetMsg());
        log.error(e.getMessage());
        return new ResponseData(e.getRetCode(), e.getRetMsg(), StringUtils.EMPTY).toString();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultVo errorParam(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        String msg = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return ResultVoUtil.error(ErrorCodeEnum.PARAM_ERROR.getRetCode(), msg);
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResultVo errorParam(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return ResultVoUtil.error(ErrorCodeEnum.PARAM_NULL.getRetCode(), ErrorCodeEnum.PARAM_NULL.getRetMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public String errorParam(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof ServiceException) {
            return new ResponseData(((ServiceException) e).getRetCode(), ((ServiceException) e).getRetMsg(), StringUtils.EMPTY).toString();
        }
        return new ResponseData(ErrorCodeEnum.SERVICE_ERROR.getRetCode(), ErrorCodeEnum.SERVICE_ERROR.getRetMsg(), StringUtils.EMPTY).toString();
    }
}
