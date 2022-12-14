package com.crc.business.independent.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.crc.common.enums.ErrorCodeEnum;
import com.crc.common.enums.UploadOperationNameEnum;
import com.crc.business.independent.dao.UploadLinkAddressDao;
import com.crc.business.independent.model.entity.FileDownloadModel;
import com.crc.business.independent.model.entity.FileModel;
import com.crc.business.independent.model.entity.FileUploadModel;
import com.crc.business.independent.model.entity.UploadLinkAddress;
import com.crc.business.independent.service.FileUploadService;
import com.crc.common.exception.ServiceException;
import com.crc.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static com.crc.common.constant.OaConstant.*;

/** @author xiaoming */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileUploadServiceImpl implements FileUploadService {
  @Autowired AmazonS3 amazonS3;

  @Value("${filestorage.bucketName}")
  private String bucketName;

  @Value("${upload.active}")
  private String active;

  @Value("${spring.profiles.active:Unknown}")
  private String profiles;

  @Autowired UploadLinkAddressDao uploadLinkAddressMapperDao;

  /**
   * 上传文件到s3
   *
   * @param file
   * @return
   */
  @Override
  public FileModel fileUpload(
          MultipartFile file,
          String userId,
          String businessType,
          String operationName,
          Integer fileType) {
    log.info("start upload file..");
    log.info("upload user:{}", userId);
    if (file == null || file.getSize() == 0) {
      throw new ServiceException(ErrorCodeEnum.PARAM_ERROR);
    }
    String key = getKeyPath(file.getOriginalFilename());
    try {
      // 校验文件格式是否合法
      Boolean  canUploaded = FileUtils.isValid(key);
      if (!canUploaded) {
        throw new ServiceException(ErrorCodeEnum.FILE_FORMAT_ERROR);
      }
      ObjectMetadata objectMetadata = getObjectMetadata(file.getContentType(), file.getSize());
      PutObjectRequest putObjectRequest =
              new PutObjectRequest(bucketName, key, file.getInputStream(), objectMetadata);
      amazonS3.putObject(putObjectRequest);
    } catch (AmazonServiceException | IOException e) {
      throw new ServiceException(ErrorCodeEnum.UPLOAD_ERROR);
    }
    FileUploadModel amazonFileModel = new FileUploadModel();
    amazonFileModel.setFileName(file.getOriginalFilename());
    amazonFileModel.setFileSize(file.getSize());
    amazonFileModel.setFileType(file.getContentType());
    amazonFileModel.setFilePath(key);

    // 存入数据库
    UploadLinkAddress uploadLinkAddress = new UploadLinkAddress();
    uploadLinkAddress.setFileName(file.getOriginalFilename());
    uploadLinkAddress.setFilePath(key);
    uploadLinkAddress.setUploadPeopleId(userId);
    if (StringUtils.isEmpty(operationName)) {
      uploadLinkAddress.setOperationName(UploadOperationNameEnum.SUMBIT.getCode());
    } else {
      uploadLinkAddress.setOperationName(operationName);
    }
    uploadLinkAddress.setCreatTime(new Date());
    uploadLinkAddress.setBusinessType(businessType);
    uploadLinkAddress.setFileType(fileType);
    uploadLinkAddressMapperDao.insert(uploadLinkAddress);
    amazonFileModel.setId(uploadLinkAddress.getId());
    log.info("upload file success");
    return amazonFileModel;
  }

  /**
   * 完成最终的上传
   *
   * @param key
   * @param uploadId
   * @param partETags
   */
  @Override
  public void completeMultipartUpload(String key, String uploadId, List<PartETag> partETags) {
    if (StringUtils.isEmpty(key)
        || StringUtils.isEmpty(uploadId)
        || partETags == null
        || partETags.isEmpty()) {
      throw new ServiceException("error", "s3文件下载文件key错误");
    }
    try {
      CompleteMultipartUploadRequest completeMultipartUploadRequest =
          new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
      amazonS3.completeMultipartUpload(completeMultipartUploadRequest);
    } catch (AmazonServiceException e) {
      throw new ServiceException("error", "s3文件下载文件错误");
    }
  }

  /**
   * s3文件下载
   *
   * @param filePath
   * @return
   */
  @Override
  public FileDownloadModel fileDownload(String filePath) {

    boolean bool = amazonS3.doesObjectExist(bucketName, filePath);
    if (!bool) {
      throw new ServiceException(ErrorCodeEnum.DOWNLOAD_PARAMETER);
    }

    GetObjectRequest request = new GetObjectRequest(bucketName, filePath);

    S3Object object = amazonS3.getObject(request);

    ObjectMetadata objectMetadata = object.getObjectMetadata();
    FileDownloadModel model = new FileDownloadModel();
    model.setStream(object.getObjectContent());
    model.setFileType(objectMetadata.getContentType());
    model.setFileSize(objectMetadata.getContentLength());
    model.setFileName(filePath.substring(filePath.lastIndexOf("/")));
    return model;
  }

  /**
   * s3文件下载临时地址
   *
   * @param filePath
   * @return
   */
  @Override
  public String presignedUrl(String filePath) {
    boolean bool = amazonS3.doesObjectExist(bucketName, filePath);
    if (!bool) {
      throw new ServiceException(ErrorCodeEnum.DOWNLOAD_PARAMETER);
    }

    long expTimeMillis = Instant.now().toEpochMilli() + 1000 * 60 * 60;
    Date expiration = new Date(expTimeMillis);

    GeneratePresignedUrlRequest generatePresignedUrlRequest =
        new GeneratePresignedUrlRequest(bucketName, filePath)
            .withMethod(HttpMethod.GET)
            .withExpiration(expiration);
    URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
    log.info("URL:{}", url.toString());
    String proxyUrl = url.toString();

    // 生产环境将域名替换为公网域名，对象存储域名不支持公网访问。
    if (StringUtils.equals(profiles, PROD)){
      proxyUrl = StringUtils.replace(proxyUrl, HXDJDPT_CRC_OSS_HN_01_CRCLOUD_COM, HXDJDPT_MOBILE_CRLANDCD_CN);
    }
    return proxyUrl;
  }

  /**
   * 获取路径 key
   *
   * @param filename 文件名
   * @return key
   */
  private String getKeyPath(String filename) {
    StringBuilder key = new StringBuilder();
    key.append(active)
        .append(new SimpleDateFormat("/yyyyMMdd/").format(new Date()))
        .append(filename);
    return key.toString();
  }

  /**
   * 生成元数据实例
   *
   * @param contentType 文件类型
   * @param fileSize 文件大小
   * @return 元数据实例
   */
  private ObjectMetadata getObjectMetadata(String contentType, long fileSize) {
    ObjectMetadata objectMetadata = new ObjectMetadata();
    objectMetadata.setContentType(contentType);
    objectMetadata.setContentLength(fileSize);
    return objectMetadata;
  }
}
