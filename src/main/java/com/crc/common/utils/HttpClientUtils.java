package com.crc.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.crc.common.config.PspConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/** httpClient工具类*/
@Slf4j
@Component
public class HttpClientUtils {

  public static final String CHARSET_UTF_8 = "utf-8";

  private static RequestConfig requestConfig = null;

  private final PspConfig pspConfig;

  private static PspConfig pspConfigStatic;

  public HttpClientUtils(PspConfig pspConfig) {
    this.pspConfig = pspConfig;
  }

  @PostConstruct
  public void init() {
    pspConfigStatic = this.pspConfig;
  }

  static {
    // 设置请求和传输超时时间
    requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000).build();
  }

  /**
   * post请求传输json参数
   *
   * @param url url地址
   * @param jsonParam 参数
   * @return
   */
  public static JSONObject pspHttpPost(String url, JSONObject jsonParam) {
    // post请求返回结果
    CloseableHttpClient httpClient = HttpClients.createDefault();
    JSONObject jsonResult = null;
    HttpPost httpPost = new HttpPost(url);
    // 设置请求头
    httpPost.setHeader("application-token", pspConfigStatic.getApplicationToken());
    httpPost.setHeader("application-id", pspConfigStatic.getApplicationId());

    // 设置请求和传输超时时间
    httpPost.setConfig(requestConfig);

    try {
      if (null != jsonParam) {
        // 解决中文乱码问题
        StringEntity entity = new StringEntity(jsonParam.toString(), CHARSET_UTF_8);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
      }
      CloseableHttpResponse result = httpClient.execute(httpPost);
      // 请求发送成功，并得到响应
      if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String str = "";
        try {
          // 读取服务器返回过来的json字符串数据
          str = EntityUtils.toString(result.getEntity(), CHARSET_UTF_8);
          // 把json字符串转换成json对象
          jsonResult = JSONObject.parseObject(str);
        } catch (Exception e) {
          log.error("post请求提交失败:" + url, e);
        }
      }
    } catch (IOException e) {
      log.error("post请求提交失败:" + url, e);
    } finally {
      httpPost.releaseConnection();
      StreamUtil.closeStream(httpClient);
    }
    return jsonResult;
  }

  /**
   * post请求传输String参数 例如：name=Jack&sex=1&type=2 Content-type:application/x-www-form-urlencoded
   *
   * @param url url地址
   * @param strParam 参数
   * @return
   */
  public static JSONObject httpPost(String url, String strParam) {
    // post请求返回结果
    CloseableHttpClient httpClient = HttpClients.createDefault();
    JSONObject jsonResult = null;
    HttpPost httpPost = new HttpPost(url);
    httpPost.setConfig(requestConfig);
    try {
      if (null != strParam) {
        // 解决中文乱码问题
        StringEntity entity = new StringEntity(strParam, CHARSET_UTF_8);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
      }
      CloseableHttpResponse result = httpClient.execute(httpPost);
      // 请求发送成功，并得到响应
      if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        String str = "";
        try {
          // 读取服务器返回过来的json字符串数据
          str = EntityUtils.toString(result.getEntity(), CHARSET_UTF_8);
          // 把json字符串转换成json对象
          jsonResult = JSONObject.parseObject(str);
        } catch (Exception e) {
          log.error("post请求提交失败:" + url, e);
        }
      }
    } catch (IOException e) {
      log.error("post请求提交失败:" + url, e);
    } finally {
      httpPost.releaseConnection();
      StreamUtil.closeStream(httpClient);
    }
    return jsonResult;
  }

  /**
   * 发送get请求
   *
   * @param url 路径
   * @return
   */
  public static String httpGet(String url) {
    // get请求返回结果
    String strResult = StringUtils.EMPTY;
    CloseableHttpClient client = HttpClients.createDefault();
    // 发送get请求
    HttpGet request = new HttpGet(url);
    request.setConfig(requestConfig);
    try {
      CloseableHttpResponse response = client.execute(request);

      // 请求发送成功，并得到响应
      if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
        // 读取服务器返回过来的json字符串数据
        HttpEntity entity = response.getEntity();
        strResult = EntityUtils.toString(entity, CHARSET_UTF_8);
      } else {
        log.error("get请求提交失败:{}", url);
      }
    } catch (IOException e) {
      log.error("get请求提交失败:" + url, e);
    } finally {
      request.releaseConnection();
      StreamUtil.closeStream(client);
    }
    return strResult;
  }

  /**
   * 用于进行Https请求的HttpClient @ClassName: SSLClient
   *
   * @date 2018年8月9日 下午1:42:07
   */
  public static class SSLClient extends DefaultHttpClient {
    public SSLClient() throws Exception {
      super();
      SSLContext ctx = SSLContext.getInstance("TLS");
      X509TrustManager tm =
          new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {}

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {}

            @Override
            public X509Certificate[] getAcceptedIssuers() {
              return null;
            }
          };
      ctx.init(null, new TrustManager[] {tm}, null);
      SSLSocketFactory ssf =
          new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      ClientConnectionManager ccm = this.getConnectionManager();
      SchemeRegistry sr = ccm.getSchemeRegistry();
      sr.register(new Scheme("https", 443, ssf));
    }
  }
}
