package com.andy.util.OSSUtil;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.andy.util.FileUtil;

/**
 * 从云端上传下载查询删除文件
 *
 * @author CNkongfu
 * @date 2016年8月31日
 */
public class FileLoadUtil {

	/**
	 * 文件上传
	 * 
	 * @param myKey
	 * @param file
	 *            要上传的文件
	 */
	public static void upLoadFileToALiyun(String myKey, File file) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(OSSClientUtil.getEndpoint(), OSSClientUtil.getAccessKeyId(),
				OSSClientUtil.getAccessKeySecret());
		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 设置上传内容类型
		meta.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		// 设置上传文件长度
		meta.setContentLength(file.length());
		// 上传文件
		try {
			PutObjectResult m_Result = ossClient.putObject(OSSClientUtil.getBucketName(), myKey, file, meta);
			System.out.println(m_Result.getETag());
		} catch (OSSException oe) {
			oe.printStackTrace();
			throw new OSSException();
		} catch (ClientException ce) {
			ce.printStackTrace();
			throw new ClientException();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();// 关闭client
		}
	}

	/**
	 * 文件下载
	 * 
	 * @param myKey
	 * @param tempFileName
	 *            要下载的文件名
	 * @return 下载的文件流
	 * @throws Exception
	 */
	public static void downloadFileFromALiyun(String myKey, String tempFileName) throws Exception {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(OSSClientUtil.getEndpoint(), OSSClientUtil.getAccessKeyId(),
				OSSClientUtil.getAccessKeySecret());
		InputStream inputStream = null;
		// 下载object到文件
		try {
			OSSObject ossObject = ossClient.getObject(new GetObjectRequest(OSSClientUtil.getBucketName(), myKey));
			inputStream = ossObject.getObjectContent();
			String newStr = new String(tempFileName.getBytes(), "UTF-8");
			File f = new File(newStr);
			FileUtil.inputstreamToFile(inputStream, f);
		} catch (OSSException oe) {
			oe.printStackTrace();
			throw new Exception(oe.getErrorMessage());
		} catch (ClientException ce) {
			ce.printStackTrace();
			throw new Exception(ce.getErrorMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {
			ossClient.shutdown();// 关闭client
			FileUtil.closeInputStream(inputStream);
		}
	}

	/**
	 * 查看Bucket中的Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
	 */
	public static void queryObject() {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(OSSClientUtil.getEndpoint(), OSSClientUtil.getAccessKeyId(),
				OSSClientUtil.getAccessKeySecret());
		try {
			// 替换后的文件
			ObjectListing objectListing = ossClient.listObjects(OSSClientUtil.getBucketName(), "customer/");
			List<OSSObjectSummary> objectSummary = objectListing.getObjectSummaries();
			System.out.println("您有以下Object：");
			for (OSSObjectSummary object : objectSummary) {
				System.out.println("\t" + object.getKey());
			}
		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();// 关闭client
		}
	}

	/**
	 * 删除Object。详细请参看“SDK手册 > Java-SDK > 管理文件”。
	 * 
	 * @param myKey
	 */
	public static void deleteSingleObect(String myKey) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(OSSClientUtil.getEndpoint(), OSSClientUtil.getAccessKeyId(),
				OSSClientUtil.getAccessKeySecret());
		try {
			ossClient.deleteObject(OSSClientUtil.getBucketName(), myKey);

		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();// 关闭client
		}
	}

	/**
	 * 批量删除object,详细请参看“SDK手册 > Java-SDK > 管理文件”。
	 */
	public static void deleteBatchObect(List<String> keys) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(OSSClientUtil.getEndpoint(), OSSClientUtil.getAccessKeyId(),
				OSSClientUtil.getAccessKeySecret());
		try {
			// 删除Objects
			DeleteObjectsResult deleteObjectsResult = ossClient
					.deleteObjects(new DeleteObjectsRequest(OSSClientUtil.getBucketName()).withKeys(keys));
			List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();// 关闭client
		}

	}

	/**
	 * 判断该Object是否存在
	 * 
	 * @param myKey
	 * @return
	 */
	public static boolean doesObjectExist(String myKey) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(OSSClientUtil.getEndpoint(), OSSClientUtil.getAccessKeyId(),
				OSSClientUtil.getAccessKeySecret());
		boolean found = false;
		try {
			found = ossClient.doesObjectExist(OSSClientUtil.getBucketName(), myKey);
		} catch (OSSException oe) {
			oe.printStackTrace();
		} catch (ClientException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ossClient.shutdown();// 关闭client
		}
		return found;
	}

	public static void main(String[] args) {
		// TODO
		// queryObject();
		// deleteBatchObect();
		// FileUtil.deleteFolder("协议计划一期.doc");
		// System.out.println(doesObjectExist("customer/协议.doc"));
	}
}
