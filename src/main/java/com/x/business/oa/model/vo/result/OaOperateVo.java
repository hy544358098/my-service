package com.x.business.oa.model.vo.result;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class OaOperateVo {
     /**
      * 主键id
      */
     @TableId
     private Integer id;
     /**
      * 操作人id
      */
     private Integer opeId;
     /**
      * 操作人姓名
      */
     private String opeName;
     /**
      * 操作时间
      */
     private String opeDate;
     /**
      * 操作内容
      */
     private String opeStatus;
     /**
      * 被操作人id
      */
     private Integer byOpeId;
     /**
      * 被操作人姓名
      */
     private String byOpeName;
     /**
      * 备注
      */
     private String opeComment;
}
