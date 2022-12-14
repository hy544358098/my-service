package com.crc.business.independent.service;

import com.amazonaws.services.s3.model.PartETag;
import com.crc.business.independent.model.entity.FileDownloadModel;
import com.crc.business.independent.model.entity.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

/** @author xiaoming */
public interface FileUploadService {
  /**
   * 上传文件到s3
   *
   * @param file
   * @return
   */
  FileModel fileUpload(MultipartFile file, String userId, String businessType,String operationName,Integer fileType);

  /**
   * 完成最终的上传
   *
   * @param key
   * @param uploadId
   * @param partETags
   */
  void completeMultipartUpload(String key, String uploadId, List<PartETag> partETags);

  /**
   * s3文件下载
   *
   * @param filePath
   * @return
   */
  FileDownloadModel fileDownload(String filePath);

  /**
   * s3文件下载临时地址
   *
   * @param filePath
   * @return
   */
  String presignedUrl(String filePath);
}
