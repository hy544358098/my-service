package com.crc.common.config;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classname:
 * Description:
 * Author: cymiao
 * Date: 2021/8/13 14:06
 **/
@Configuration
public class S3Config {

    @Value("${filestorage.endpoint}")
    private String endpoint;

    @Value("${filestorage.accessKey}")
    private String accessKey;

    @Value("${filestorage.secretKey}")
    private String secretKey;

    @Value("${filestorage.useSsl:true}")
    private boolean useSsl;

    @Bean
    public AmazonS3 amazonS3(){
        ClientConfiguration config = new ClientConfiguration().withProtocol(useSsl ? Protocol.HTTPS : Protocol.HTTP);
        config.setSignerOverride("S3SignerType");
        AwsClientBuilder.EndpointConfiguration endpointConfig =
                new AwsClientBuilder.EndpointConfiguration(endpoint, "");

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,secretKey);
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withClientConfiguration(config)
                .withPathStyleAccessEnabled(false)
                .withEndpointConfiguration(endpointConfig)
                .withCredentials(awsCredentialsProvider)
                .build();
        return amazonS3;
    }
}
