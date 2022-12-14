package com.x.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description: TODO
 * @Author: xiaoming
 * @Date: 2021/11/5 11:21
 * @Version: 1.0
 */
@Data
@TableName("t_oa_dept")
public class DepartmentEntity {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("部门名称短名称")
    private String shortDeptName;

    private Integer type;
}
