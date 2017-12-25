package org.pussinboots.ttui.administrator.pojo.dto;

import org.pussinboots.ttui.administrator.entity.Customer;
import org.pussinboots.ttui.common.support.page.PageInfo;

import java.io.Serializable;
import java.util.List;

public class CustomerPageDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 用户列表信息
	 */
	private List<Customer> userVOs;

	/**
	 * 分页信息
	 */
	private PageInfo pageInfo;

	public CustomerPageDTO(List<Customer> userVOs, PageInfo pageInfo) {
		super();
		this.userVOs = userVOs;
		this.pageInfo = pageInfo;
	}

	public List<Customer> getUserVOs() {
		return userVOs;
	}

	public void setUserVOs(List<Customer> userVOs) {
		this.userVOs = userVOs;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
