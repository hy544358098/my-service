package com.crc.common.exception;

/**
 * @Description 操作异常编码
 * @Author YANKAIBO
 * @Date 2021/10/25 11:20
 * @Version 1.0
 */
public class OperationErrorCode {
    /***
     * 操作已办结的流程
     */
    public static final String OPERATION_ERROR_0001 = "operation_error_0001";

    /***
     * 已转办的数据不能再知会给同一个人
     */
    public static final String OPERATION_ERROR_0002 = "operation_error_0002";

    /***
     * 已知会的数据不能再知会给同一个人
     */
    public static final String OPERATION_ERROR_0003 = "operation_error_0003";

    /***
     * 操作人与被操作人是同一个人
     */
    public static final String OPERATION_ERROR_0004 = "operation_error_0004";
}
