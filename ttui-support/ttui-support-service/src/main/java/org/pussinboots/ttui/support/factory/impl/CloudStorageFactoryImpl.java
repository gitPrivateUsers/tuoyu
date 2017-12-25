package org.pussinboots.ttui.support.factory.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.pussinboots.ttui.support.common.enums.CloudServiceEnum;
import org.pussinboots.ttui.support.factory.CloudStorageFactory;
import org.pussinboots.ttui.support.service.IAliyunCloudStorageService;
import org.pussinboots.ttui.support.service.IBaseCloudStorageService;
import org.pussinboots.ttui.support.service.IQiniuCloudStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：ttui-support-service
* 类名称：CloudStorageFactoryImpl   
* 类描述：CloudStorageFactoryImpl 云存储工厂类接口实现   
* 创建人：陈星星   
* 创建时间：2017年7月30日 下午10:28:02   
*
 */
@Service
public class CloudStorageFactoryImpl implements CloudStorageFactory {
	
	@Autowired
	private IAliyunCloudStorageService aliyunCloudStorageService;
	
	@Autowired
	private IQiniuCloudStorageService qiniuCloudStorageService;
	
	/**
	 * 云存储处理器容器
	 */
	private Map<CloudServiceEnum, IBaseCloudStorageService> cloudServiceMap = new HashMap<>();
	
	/**
	 * 初始化处理器容器
	 */
	@PostConstruct
	public void init() {
		// 阿里云云存储处理器
		cloudServiceMap.put(aliyunCloudStorageService.getCloudServiceType(), aliyunCloudStorageService);
		// 七牛云云存储处理器
		cloudServiceMap.put(qiniuCloudStorageService.getCloudServiceType(), qiniuCloudStorageService);
	}
	
	@Override
	public IBaseCloudStorageService getBaseCloudStorageService(CloudServiceEnum cloudServiceEnum) {
		return cloudServiceMap.get(cloudServiceEnum);
	}
}
