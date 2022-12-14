package com.crc.business.independent.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 其他服务
 * </p>
 *
 * @author wangxy
 * @since 2021-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
//@TableName("cpps_other_service")
public class OtherService implements Serializable {

    private static final long serialVersionUID=1L;
    /**
     * 其他服务ID
     */
    private String id;

    /**
     * 服务类型
     */
    private String serviceType;

    /**
     * 服务编码
     */
    private String serviceCode;

    /**
     * 服务名称
     */
    private String serviceName;

    /**
     * 适用对象
     */
    private String applicableObjects;

    /**
     * 服务价格(不含税)
     */
    private String servicePrice;

    /**
     * 税率
     */
    private String taxRate;

    /**
     * 服务说明
     */
    private String serviceDescription;

    /**
     * 价格有效期开始
     */
    private Date startTime;

    /**
     * 价格有效期结束
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 创建人
     */
    private String createUsername;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 修改人
     */
    private String modifyUsername;

    /**
     * 修改人ID
     */
    private Long modifyUserId;

    /**
     * 是否删除1删除0未删除
     */
    private Integer isDelete;

    /**
     * 是否审批0待审批1已审批
     */
    private Integer isApprove;

    /**
     * 排序
     */
    private Integer orderCol;

    /**
     * 数据类型
     */
    private String typeData;

    /**
     * 外包资源供应商名称
     */
    private String outResourceName;


}
