package org.pussinboots.ttui.online.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.pussinboots.ttui.common.support.email.EmailMsg;
import org.pussinboots.ttui.common.support.email.EmailSendManager;
import org.pussinboots.ttui.common.util.DateUtils;
import org.pussinboots.ttui.online.common.enums.EmailSendStatusEnum;
import org.pussinboots.ttui.online.common.enums.EmailTypeEnum;
import org.pussinboots.ttui.online.common.util.EmailUtils;
import org.pussinboots.ttui.online.entity.Email;
import org.pussinboots.ttui.online.mapper.EmailMapper;
import org.pussinboots.ttui.online.pojo.dto.EmailSendResultDTO;
import org.pussinboots.ttui.online.service.IEmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 
* 项目名称：ttui-online-service
* 类名称：EmailSendServiceImpl   
* 类描述：EmailSend / 邮件发送 业务逻辑层接口实现类        
* 创建人：陈星星   
* 创建时间：2017年4月8日 下午4:07:42   
*
 */
@Service("emailSendService")
public class EmailSendServiceImpl implements IEmailSendService{
    
	@Autowired
	private EmailMapper emailMapper;
	
	@Override
	public EmailSendResultDTO sendMailByVelocity(String email, String userName, Integer type) {
		
		Date startTime = new Date(); // 发送时间
		Date endTime = DateUtils.getOffsiteDate(startTime, Calendar.MINUTE, EmailUtils.getCaptchaTime());// 验证时间向后偏移3分钟
		String captcha = EmailUtils.getCaptcha(); // 验证码
		Map<String, Object> model = new HashMap<>();
		model.put("createTime", startTime);
		model.put("captcha", captcha);
		model.put("email", email);
		model.put("userNumber", userName);
		
		EmailMsg emailMsg = new EmailMsg();
		emailMsg.setToEmails(email); // 收件人
		emailMsg.setSubject(EmailTypeEnum.stateOf(type).getEmailSubject());// 邮件主题
		emailMsg.setVelocityTemplate(EmailTypeEnum.stateOf(type).getVelocityTemplate());// 模版
		emailMsg.setModel(model);// 邮件正文
		
		EmailSendManager emailSendManager = new EmailSendManager();
		Boolean result = emailSendManager.sendMail(emailMsg);
		Email emailRecord = new Email(EmailUtils.getEmailSign(), email, EmailTypeEnum.stateOf(type).getType(), startTime,
				startTime, endTime, EmailSendStatusEnum.resultOf(result).getStatus(), captcha,
				EmailTypeEnum.stateOf(type).getEmailSubject(), JSON.toJSON(model).toString());
		emailMapper.insert(emailRecord);
		return new EmailSendResultDTO(result, emailRecord.getEmailSign());
	}
}