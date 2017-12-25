package org.pussinboots.ttui.cms.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.ttui.cms.common.util.ServletUtils;
import org.pussinboots.ttui.common.base.BaseController;
import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.system.common.enums.CommonConstantEnum;
import org.pussinboots.ttui.system.entity.VersionLog;
import org.pussinboots.ttui.system.service.IVersionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* 项目名称：ttui-cms-web Maven Webapp
* 类名称：SystemVersionLogControlller   
* 类描述：版本日志表示层控制器    
* 创建人：陈星星   
* 创建时间：2017年4月7日 下午2:22:20   
*
 */
@Controller
@RequestMapping(value = "/system/version")
@Api(value = "版本日志", description = "版本日志")
public class SystemVersionLogController extends BaseController {
	
	@Autowired
	private IVersionLogService versionLogService;
	
	/**
	 * GET 版本日志页面
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "版本日志", notes = "版本日志")
	@RequiresPermissions("system:version:view")
	@GetMapping(value = "/view")
	public String list(Model model) {
		Integer limit = StringUtils.isNumeric(ServletUtils.getParameter("limit"))
				? Integer.valueOf(ServletUtils.getParameter("limit")) : CommonConstantEnum.VERSION_NUMBER.getValue();
		Integer current = StringUtils.isNumeric(ServletUtils.getParameter("current"))
				? Integer.valueOf(ServletUtils.getParameter("current")) : 1;
		PageInfo pageInfo = new PageInfo();
		pageInfo.setLimit(limit);
		pageInfo.setCurrent(current);

		BasePageDTO<VersionLog> basePageDTO = versionLogService.listByPullPage(pageInfo);
		model.addAttribute("versionLogs", basePageDTO.getList());
		model.addAttribute("pageInfo", basePageDTO.getPageInfo());
		return "/modules/version/system_version_list";
	}
}
