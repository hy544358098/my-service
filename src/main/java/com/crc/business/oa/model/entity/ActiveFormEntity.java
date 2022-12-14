package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA动态表格表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-21 17:10:04
 */
@Data
@TableName("t_oa_active_form")
public class ActiveFormEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 自增主键
	 */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	/**
	 * 简易的UUID，确保唯一，关联主表
	 */
	private String uid;
	/**
	 * 动态表格名
	 */
	private String tableName;
	/**
	 * 动态表格内容
	 */
	private String tableInfo;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
