package com.crc.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.crc.business.independent.model.entity.OperationDetail;
import com.crc.business.independent.model.vo.request.OtherServicePageParam;
import com.crc.business.independent.model.vo.result.OperationDetailVo;
import com.crc.business.independent.model.vo.result.ResultVo;

/**
 * @author xiaoming
 * @date 2021-09-10 14:37:27
 */
public interface OperationDetailService extends IService<OperationDetail> {
  void add(OperationDetailVo operationDetailVo);
}
