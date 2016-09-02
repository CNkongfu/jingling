package com.andy.util.OSSUtil;

/**
 * OSSClient
 *
 * @author CNkongfu
 * @date 2016年8月31日
 */
public class OSSClientUtil {
	// 访问OSS的域名
	private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
	private static String accessKeyId = "";
	private static String accessKeySecret = "";
	private static String bucketName = "";

	public static String getEndpoint() {
		return endpoint;
	}

	public static void setEndpoint(String endpoint) {
		OSSClientUtil.endpoint = endpoint;
	}

	public static String getAccessKeyId() {
		return accessKeyId;
	}

	public static void setAccessKeyId(String accessKeyId) {
		OSSClientUtil.accessKeyId = accessKeyId;
	}

	public static String getAccessKeySecret() {
		return accessKeySecret;
	}

	public static void setAccessKeySecret(String accessKeySecret) {
		OSSClientUtil.accessKeySecret = accessKeySecret;
	}

	public static String getBucketName() {
		return bucketName;
	}

	public static void setBucketName(String bucketName) {
		OSSClientUtil.bucketName = bucketName;
	}

}
