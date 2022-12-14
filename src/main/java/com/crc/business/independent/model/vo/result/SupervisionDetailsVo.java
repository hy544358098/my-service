package com.crc.business.independent.model.vo.result;

import com.crc.business.independent.model.entity.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/** @author xiaoming */
@Data
public class SupervisionDetailsVo {

  @ApiModelProperty("我要观察表数据")
  private Supervision supervision;

  @ApiModelProperty("纪检观察表数据")
  private SupervisionDatabaseInspection supervisionDatabase;

  @ApiModelProperty("业务观察表数据")
  private SupervisionDatabaseBusiness supervisionDatabaseBusiness;

  @ApiModelProperty("上传表集合")
  private List<UploadLinkAddress> uploadLinkAddress;

  @ApiModelProperty("操作表集合")
  private List<OperationDetail> operationDetail;
}
