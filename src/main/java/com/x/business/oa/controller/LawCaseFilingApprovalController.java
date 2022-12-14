package com.x.business.oa.controller;

import com.alibaba.fastjson.JSONObject;
import com.x.business.oa.model.vo.request.LawCaseFilingApprovalParam;
import com.x.business.oa.service.LawCaseFilingApprovalService;
import com.x.runwork.core.ssdp.ResponseData;
import com.x.runwork.core.ssdp.ReturnCode;
import com.x.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 法律合规-案件处理方案审批
 * @Author xiaoming
 * @Date 2021/10/22 13:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("api/law/casefilingapproval")
@Api(tags = "oa_法律合规-案件处理方案审批", value = "oa_法律合规-案件处理方案审批")
public class LawCaseFilingApprovalController extends BaseController {
    @Autowired
    private LawCaseFilingApprovalService lawCaseFilingApprovalService;
    private Logger logger = LoggerFactory.getLogger(LawCaseFilingApprovalController.class);

    @ApiOperation(value = "统计图表")
    @PostMapping("/statistics")
    public String statistics(@RequestBody JSONObject ssdpVo) {
        log.info("------query-------->" + ssdpVo.toJSONString());
        try {
            //获取业务参数
            LawCaseFilingApprovalParam param =
                    this.getBusDataBase64DecodeToJavaObject(ssdpVo, LawCaseFilingApprovalParam.class);
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , lawCaseFilingApprovalService.statistics(param)).toString();
        } catch (Exception e) {
            logger.error("login error", e);
            return new ResponseData(ReturnCode.E0S00020, ReturnCode.E0S00020_DESC).toString();
        }
    }
}
