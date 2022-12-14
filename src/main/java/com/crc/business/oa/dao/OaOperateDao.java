package com.crc.business.oa.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.crc.business.oa.model.entity.OaOperateEntity;
import com.crc.business.oa.model.vo.result.PendingVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户操作信息表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2021-09-21 17:10:04
 */
@Mapper
public interface OaOperateDao extends BaseMapper<OaOperateEntity> {

    /**
     * @Description 查询各重点观察类型的转办数据
     * @Date 2021/10/27 9:30
     * @param
     * @Return {@link List < SpecialOperateEntity>}
     */
    List<OaOperateEntity> timingRemind();

    /**
     * @Description 查询表状态
     * @Date 2021/10/29 10:41
     * @param
     * @Return {@link String}
     */
    String findStatus(@Param("uid") String uid, @Param("tableName") String tableName);

    /**
     * @Description 查询在此操作之后有没有反馈数据
     * @Date 2021/10/29 10:41
     * @param
     * @Return {@link String}
     */
    String findOperationTime(OaOperateEntity specialOperateEntity);

    /**
     * @Description 查询操作权限
     * @Date 2021/12/13 16:28
     * @param supervisionId
     * @param operationPeopleId
     * @Return {@link com.crc.business.oa.model.entity.OaOperateEntity}
     */
    List<OaOperateEntity> findCompetence(@Param("supervisionId")String supervisionId, @Param("operationPeopleId")String operationPeopleId);


    /**
     * @Description 查询操作权限
     * @Date 2021/12/15 17:37
     * @param pending
     * @Return {@link List< String>}
     */
    List<String> findCompetenceNew(@Param("list") List<String> pending,@Param("userId") String userId);
}
