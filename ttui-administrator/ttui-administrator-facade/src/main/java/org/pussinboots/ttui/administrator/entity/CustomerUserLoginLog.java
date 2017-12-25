package org.pussinboots.ttui.administrator.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("os_user_login_log")
public class CustomerUserLoginLog extends Model<CustomerUserLoginLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录日志ID
     */
	@TableId(value="log_id", type= IdType.AUTO)
	private Long logId;
    /**
     * 登录时间
     */
	@TableField("login_time")
	private Date loginTime;
    /**
     * 登录IP
     */
	@TableField("user_ip")
	private String userIp;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 操作系统
     */
	@TableField("operating_system")
	private String operatingSystem;
    /**
     * 浏览器
     */
	private String browser;

	public CustomerUserLoginLog() {
		super();
	}

	public CustomerUserLoginLog(Date loginTime, String userIp, Long userId, String operatingSystem,
								String browser) {
		super();
		this.loginTime = loginTime;
		this.userIp = userIp;
		this.userId = userId;
		this.operatingSystem = operatingSystem;
		this.browser = browser;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	protected Serializable pkVal() {
		return this.logId;
	}

}
