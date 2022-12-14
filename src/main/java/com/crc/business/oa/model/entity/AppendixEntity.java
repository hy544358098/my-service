package com.crc.business.oa.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * OA附件表
 * 
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-21 17:10:04
 */
@Data
@TableName("t_oa_appendix")
public class AppendixEntity implements Serializable {
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
	 * 附件名
	 */
	private String appendixName;
	/**
	 * 预览链接
	 */
	private String appendixUrl;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 下载链接
	 */
	private String downloadUrl;

}
