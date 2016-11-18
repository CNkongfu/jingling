package com.jingling.core.util.aliyun_OSS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * OSSClient
 *
 * @author chengjinling
 * @date 2016年8月31日
 */
@Service
public class OSSClientUtil {
    // 访问OSS的域名
    public static final String ENDPOINT = "http://img-cn-shanghai.aliyuncs.com";// http://oss-cn-shanghai.aliyuncs.com
    public static final String ACCESSKEYID = "1r1iO14dxveFMf3z";
    public static final String ACCESSKEYSECRET = "MS63SwYLWbghusJ3GgxJaPfsmepilE";
    // 这个OSS用来上传图片
    @Value("${bucketName}")
    private String bucketName;

    public String getBucketName() {
        return this.bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

}
