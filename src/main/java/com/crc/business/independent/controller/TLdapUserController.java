package com.crc.business.independent.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.model.entity.TLdapUser;
import com.crc.business.independent.model.vo.request.TLdapUserPO;
import com.crc.business.independent.model.vo.result.ResultVo;
import com.crc.business.independent.service.ITLdapUserService;
import com.crc.common.utils.ResultVoUtil;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * LDAP用户信息
 *
 * @author xiaoming
 * @date 2021-09-15 14:49:59
 */
@RestController
@RequestMapping("api/tldapuser")
@Api(tags = "LDAP用户信息")
public class TLdapUserController extends BaseController {
    @Autowired
    private ITLdapUserService tLdapUserService;
    private Logger logger = LoggerFactory.getLogger(TLdapUserController.class);

    /**
     * 查询用户列表
     */
    @ApiOperation(value = "查询用户列表")
    @PostMapping(value = "/getUserList")
    public String getUserList(
            @RequestHeader(value = "userId") String userId, @RequestBody TLdapUserPO tLdapUserPO) {
        try {
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , tLdapUserService.getUserList(tLdapUserPO, userId)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }

    /**
     * 根据部门编码查询用户列表
     */
    @ApiOperation(value = "根据部门编码查询用户列表")
    @PostMapping(value = "/getUserByOrgCode")
    public ResultVo getUserByOrgCode(@RequestBody TLdapUserPO tLdapUserPO) {
        return ResultVoUtil.success(tLdapUserService.getUserByOrgCode(tLdapUserPO));
    }

    /**
     * 查询单个用户信息
     */
    @ApiOperation(value = "查询单个用户信息")
    @PostMapping("/getUserInfo")
    public String getUserInfo(@RequestBody JSONObject ssdpVo) {
        TLdapUserPO tLdapUserPO = this.getBusDataBase64DecodeToJavaObject(ssdpVo, TLdapUserPO.class);
        if (tLdapUserPO == null) {
            return new ResponseData(ReturnCode.E0M00002, ReturnCode.E0M00002_DESC, "demo参数为空").toString();
        }
        try {
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , tLdapUserService.getUserInfo(tLdapUserPO)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新增")
    @PostMapping("/add")
    public ResultVo save(@RequestBody TLdapUser tLdapUser) {
        tLdapUserService.save(tLdapUser);
        return ResultVoUtil.success();
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改")
    @PostMapping("/update")
    public ResultVo update(@RequestBody TLdapUser tLdapUser) {
        tLdapUserService.updateById(tLdapUser);

        return ResultVoUtil.success();
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public ResultVo delete(@RequestBody String[] ids) {
        tLdapUserService.removeByIds(Arrays.asList(ids));

        return ResultVoUtil.success();
    }
}
