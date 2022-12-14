package com.crc.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description psp服务配置
 *
 * @author xiaoming
 * @date 2021/9/20 18:15
 */
@Data
@Configuration
public class PspConfig {

  @Value("${psp.applicationId}")
  public String applicationId;

  @Value("${psp.applicationToken}")
  public String applicationToken;

  @Value("${psp.url}")
  public String url;
}
