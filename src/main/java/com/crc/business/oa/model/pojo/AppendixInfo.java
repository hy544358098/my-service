package com.crc.business.oa.model.pojo;

import lombok.Data;

@Data
public class AppendixInfo {

    /**
     * 附件名
     */
    private String appendixName;

    /**
     * 预览链接
     */
    private String appendixUrl;
    /**
     * 下载链接
     */
    private String downloadUrl;
}
