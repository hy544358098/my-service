package com.x.business.independent.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.x.business.independent.model.entity.TLdapUser;
import com.x.business.independent.model.vo.request.TLdapUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author BEYONDSOFT-101
 */
@Mapper
public interface TLdapUserDao extends BaseMapper<TLdapUser> {

    /**
     * 列表分页查询
     *
     * @return
     */
    Page<TLdapUser> list(Page<TLdapUser> page, TLdapUserPO param);
}