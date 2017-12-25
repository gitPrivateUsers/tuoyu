package org.pussinboots.ttui.cms.controller.customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.pussinboots.ttui.administrator.entity.Customer;
import org.pussinboots.ttui.administrator.pojo.dto.CustomerPageDTO;
import org.pussinboots.ttui.administrator.service.ICustomerService;
import org.pussinboots.ttui.cms.common.result.CmsPageResult;
import org.pussinboots.ttui.cms.common.result.CmsResult;
import org.pussinboots.ttui.cms.common.security.AuthorizingUser;
import org.pussinboots.ttui.cms.common.util.SingletonLoginUtils;
import org.pussinboots.ttui.common.base.BaseController;
import org.pussinboots.ttui.common.constant.CommonReturnCode;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

/**
 *
 * 项目名称：ttui-cms-web Maven Webapp
 * 类名称：CustomerController
 * 类描述：用户管理表示层控制器
 * 创建人：zhancl
 * 创建时间：2017年12月21日 上午10:12:17
 *
 */
@Controller
@RequestMapping(value = "/customer/detail")
@Api(value = "用户管理", description = "用户管理")
public class CustomerController extends BaseController {
    @Autowired
    private ICustomerService customerService;

    /**
     * GET 管理员列表页面
     * @param model
     * @return
     */
    @ApiOperation(value = "用户列表页面", notes = "用户列表页面")
    @RequiresPermissions("customer:list:view")
    @GetMapping(value = "/view")
    public String getListPage(Model model) {
        return "/modules/customer/customer_list";
    }

    /**
     * GET 管理员列表
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "根据分页信息获取用户列表")
    @RequiresPermissions("customer:list:view")
    @GetMapping(value = "/info")
    @ResponseBody
    public Object customerList(PageInfo pageInfo, @RequestParam(required = false, value = "search") String search) {
        CustomerPageDTO customerPageDTO = customerService.listByPage(pageInfo, search);
        return new CmsPageResult(customerPageDTO.getUserVOs(), customerPageDTO.getPageInfo().getTotal());
    }


    /**
     * GET 创建product
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @RequiresPermissions("customer:detail:create")
    @GetMapping(value = "/create")
    public String getInsertPage(Model model) {
        return "/modules/customer/customer_create";
    }

    /**
     * POST 创建商品
     * @return
     */
    @ApiOperation(value = "创建用户", notes = "创建用户")
    @RequiresPermissions("customer:detail:create")
    @PostMapping(value = "")
    @ResponseBody
    public Object insert(Customer customer) {
        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            Integer count = customerService.insertCustomer(customer, authorizingUser.getUserName());
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

    /**
     * GET 更新商品信息
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息")
    @RequiresPermissions("customer:detail:edit")
    @GetMapping(value = "/{userId}/edit")
    public String getUpdatePage(Model model, @PathVariable("userId") Long userId) {
        // 广告信息
        Customer customer = customerService.selectById(userId);
        model.addAttribute("customer", customer);

        return "/modules/customer/customer_update";
    }

    /**
     * PUT 更新商品信息
     * @return
     */
    @ApiOperation(value = "更新用户信息", notes = "根据ID修改")
    @RequiresPermissions("customer:detail:edit")
    @PutMapping(value = "/{userId}")
    @ResponseBody
    public Object update(Customer customer, @PathVariable("userId") Long userId) {

        AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
        if (authorizingUser != null) {
            // 更新用户记录
            Integer count = customerService.updateUserId(customer, authorizingUser.getUserName());
            return new CmsResult(CommonReturnCode.SUCCESS, count);
        } else {
            return new CmsResult(CommonReturnCode.UNAUTHORIZED);
        }
    }

}
