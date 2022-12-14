package com.crc.business.independent.controller;

import com.crc.business.independent.service.FileUploadService;
import com.crc.runwork.core.ssdp.ResponseData;
import com.crc.runwork.core.ssdp.ReturnCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author xiaoming
 */
@RestController
@Api(value = "", description = "文件上传")
@RequestMapping("api/fileOperation")
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(UploadController.class);
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 上传文件到s3
     *
     * @param file
     * @return
     */
    @PostMapping("/fileUpload")
    @ApiOperation(value = "上传文件")
    public String fileUpload(MultipartFile file, String businessType, String operationName, Integer fileType, HttpServletRequest req) {

            String userId = req.getHeader("userId");
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , fileUploadService.fileUpload(file, userId, businessType, operationName, fileType)).toString();
    }

    /**
     * s3文件下载
     *
     * @param filePath
     */
    @PostMapping("/presignedUrl")
    @ApiOperation(value = "下载文件")
    public String fileDownload(@RequestBody String filePath) {
            return new ResponseData(ReturnCode.S000A000, ReturnCode.S000A000_DESC
                    , fileUploadService.presignedUrl(filePath)).toString();
    }
}
