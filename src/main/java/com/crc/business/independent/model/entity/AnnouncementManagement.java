package com.crc.business.independent.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AnnouncementManagement
 * @Description: 公告实体
 * @Author: xiaoming
 * @Date: 2021/10/18 15:54
 * @Version: 1.0
 */
@Data
@TableName("t_announcement_management")
public class AnnouncementManagement extends BaseEntity<AnnouncementManagement>{

    @ApiModelProperty("uuid")
    private String uid;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("1重要/0普通")
    private Integer type;

    @ApiModelProperty("是否置顶")
    private Integer topType;
}
