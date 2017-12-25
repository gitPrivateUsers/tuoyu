package org.pussinboots.ttui.administrator.pojo.vo;

import java.util.List;

import org.pussinboots.ttui.administrator.entity.Role;
import org.pussinboots.ttui.administrator.entity.User;

public class UserVO extends User{

	private static final long serialVersionUID = 1L;
	
	/** 组织名称 */
	private String organizationName;
	
	/** 用户角色 */
	private List<Role> roles;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
