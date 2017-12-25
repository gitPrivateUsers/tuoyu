package org.pussinboots.ttui.cms.controller.order;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.ttui.cms.common.result.CmsPageResult;
import org.pussinboots.ttui.common.base.BaseController;
import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.constant.CommonReturnCode;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.order.entity.*;
import org.pussinboots.ttui.order.pojo.vo.OrderVO;
import org.pussinboots.ttui.order.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
* 类名称：OrderController
* 类描述：订单管理表示层控制器
*
 */
@Controller
@RequestMapping(value = "/system/order")
@Api(value = "订单管理", description = "订单管理")
public class OrderController extends BaseController {

	@Autowired
	private IOrderService orderService;
	@Autowired
	private IOrderProductService orderProductService;
	@Autowired
	private IOrderShipmentService orderShipmentService;
	@Autowired
	private IOrderStatusService orderStatusService;
	

	/**
	 * GET 订单管理页面
	 * @return
	 */
	@ApiOperation(value = "订单管理页面", notes = "订单管理页面")
	@RequiresPermissions("order:list:view")
	@GetMapping(value = "/view")
	public String getorderPage(Model model) {
		return "/modules/order/order_list"; /* 订单列表返回路径 */
	}
	
	/**
	 * GET 订单列表,
	 * @return
	 */
	@ApiOperation(value = "获取订单列表", notes = "根据分页信息/搜索内容")
	@RequiresPermissions("order:list:view")
	@GetMapping(value = "/list")
	@ResponseBody
	public Object listorder(Long userId,PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
		BasePageDTO<OrderVO> basePageDTO = orderService.list(userId, pageInfo, search, search);
		return new CmsPageResult(basePageDTO.getList(), basePageDTO.getPageInfo().getTotal());
		/*return null;*/
	}



}
