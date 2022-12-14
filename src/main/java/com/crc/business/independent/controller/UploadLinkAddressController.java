package com.crc.business.independent.controller;

import com.alibaba.fastjson.JSONObject;
import com.crc.business.independent.service.UploadLinkAddressService;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import com.crc.runwork.core.ssdp.base.BaseController;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author xiaoming
 * @date 2021-09-15 17:47:57
 */
@Slf4j
@RestController
@RequestMapping("api/uploadlinkaddress")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadLinkAddressController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UploadLinkAddressController.class);
    private final UploadLinkAddressService uploadLinkAddressService;

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @PostMapping("/delete")
    public String delete(@RequestBody Long[] ids) {
            uploadLinkAddressService.removeByIds(Arrays.asList(ids));
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC).toString();
    }
}
