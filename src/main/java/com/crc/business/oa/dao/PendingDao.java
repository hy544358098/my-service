package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crc.business.oa.model.vo.request.PendingParam;
import com.crc.business.oa.model.vo.result.InformVo;
import com.crc.business.oa.model.vo.result.PendingVo;
import com.crc.business.oa.model.vo.result.ProjectChangeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author xiaoming
 * @Date 2021/12/3 15:51
 * @Version 1.0
 */

public interface PendingDao {
    /**
     * @Description 待办
     * @Date 2021/12/6 10:11
     * @param param
     * @Return {@link List< PendingVo>}
     */
    Page<PendingVo> pending(Page<PendingVo> page, PendingParam param);

    /**
     * @Description 待办
     * @Date 2021/12/6 10:11
     * @param param
     * @Return {@link List< PendingVo>}
     */
    List<PendingVo> pending(@Param("param")PendingParam param);

    /**
     * @Description 已办
     * @Date 2021/12/6 10:11
     * @param param
     * @Return {@link List< PendingVo>}
     */
    Page<PendingVo> already(Page<PendingVo> page,PendingParam param);


    /**
     * @Description 非大区观察管理员待办
     * @Date 2021/12/6 10:11
     * @param param
     * @Return {@link List< PendingVo>}
     */
    Page<PendingVo> findCompetence(Page<PendingVo> page,PendingParam param);


    /**
     * @Description 非大区观察管理员待办
     * @Date 2021/12/6 10:11
     * @param param
     * @Return {@link List< PendingVo>}
     */
    Page<InformVo> informList(Page<PendingVo> page, PendingParam param);
}
