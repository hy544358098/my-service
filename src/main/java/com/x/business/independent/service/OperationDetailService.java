package com.x.business.independent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.x.business.independent.model.entity.OperationDetail;
import com.x.business.independent.model.vo.result.OperationDetailVo;

/**
 * @author xiaoming
 * @date 2021-09-10 14:37:27
 */
public interface OperationDetailService extends IService<OperationDetail> {
  void add(OperationDetailVo operationDetailVo);
}
