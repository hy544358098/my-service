package com.crc.runwork.core.ssdp;

/**
 * 返回ssdp响应码
 * 项目组根据自己需要增加响应码
 */
public class ReturnCode {

	/* 成功响应码 */
	public static final String S000A000 = "S0A00000";
	public static final String S000A000_DESC = "success ";

	/* 参数异常响应码 */
	public static final String E0M00002 = "E0M00002";
	public static final String E0M00002_DESC = "参数为空.";

	/* 其它异常响应码 */
	public static final String E0S00020 = "E0S00020";
	public static final String E0S00020_DESC = "润工作系统异常.";


}
