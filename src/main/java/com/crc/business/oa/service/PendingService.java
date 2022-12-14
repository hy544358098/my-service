package com.crc.business.oa.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.oa.model.vo.request.PendingParam;
import com.crc.business.oa.model.vo.result.InformVo;
import com.crc.business.oa.model.vo.result.PendingVo;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/3 15:51
 * @Version 1.0
 */

public interface PendingService {

    Page<PendingVo> list(PendingParam pendingParam,String roleNames);

    Page<InformVo>  informList(PendingParam pendingParam);
}
