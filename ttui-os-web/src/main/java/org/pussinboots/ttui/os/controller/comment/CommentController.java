package org.pussinboots.ttui.os.controller.comment;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.pussinboots.ttui.common.base.BaseController;
import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.constant.CommonReturnCode;
import org.pussinboots.ttui.common.enums.StatusEnum;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.os.common.result.OsResult;
import org.pussinboots.ttui.os.common.security.AuthorizingUser;
import org.pussinboots.ttui.os.common.util.SingletonLoginUtils;
import org.pussinboots.ttui.product.common.enums.CommentSortEnum;
import org.pussinboots.ttui.product.common.enums.QuestionSortEnum;
import org.pussinboots.ttui.product.entity.Category;
import org.pussinboots.ttui.product.entity.CommentReply;
import org.pussinboots.ttui.product.entity.ProductAttribute;
import org.pussinboots.ttui.product.pojo.vo.CommentVO;
import org.pussinboots.ttui.product.pojo.vo.ProductVO;
import org.pussinboots.ttui.product.service.ICategoryService;
import org.pussinboots.ttui.product.service.ICommentReplyService;
import org.pussinboots.ttui.product.service.ICommentService;
import org.pussinboots.ttui.product.service.IProductAttributeService;
import org.pussinboots.ttui.product.service.IProductService;
import org.pussinboots.ttui.user.pojo.vo.UserVO;
import org.pussinboots.ttui.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* 项目名称：ttui-os-web Maven Webapp
* 类名称：CommentController   
* 类描述：商品评论表示层控制器         
* 创建人：陈星星   
* 创建时间：2017年5月9日 下午12:29:33   
*
 */
@Controller
@RequestMapping(value = "/comment")
@Api(value = "商品评论", description = "商品评论")
public class CommentController extends BaseController{
	
	@Autowired
	private ICommentService commentService;
	@Autowired
	private ICommentReplyService commentReplyService;
	@Autowired
	private IProductService productService;
	@Autowired
	private ICategoryService categoryService;
	@Autowired
	private IProductAttributeService productAttributeService;
	@Autowired
	private IUserService userService;
	
	/**
	 * GET 商品详情页面最有帮助的评价
	 * @return
	 */
	@ApiOperation(value = "商品详情页面最有帮助的评价", notes = "商品详情页面最有帮助的评价")
	@GetMapping(value = "/supList")
	public String supComment(Model model, @RequestParam(value = "productId", required = true) Long productId,
			@RequestParam(value = "sort", required = false, defaultValue = "1") Integer sort,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

		PageInfo pageInfo = new PageInfo(page, limit, QuestionSortEnum.typeOf(sort).getSort(),
				QuestionSortEnum.typeOf(sort).getOrder());

		BasePageDTO<CommentVO> basePageDTO = commentService.listByProductId(productId, StatusEnum.SHOW.getStatus(),
				pageInfo);
		model.addAttribute("commentVOs", basePageDTO.getList());// 评论列表
		model.addAttribute("pageInfo", basePageDTO.getPageInfo());// 分页信息

		return "/modules/comment/sup_comment";
	}
	
	/**
	 * GET 商品详情页面最新的评价
	 * @return
	 */
	@ApiOperation(value = "商品详情页面最新的评价", notes = "商品详情页面最新的评价")
	@GetMapping(value = "/tileLineList")
	public String tileLineList(Model model, @RequestParam(value = "productId", required = true) Long productId,
			@RequestParam(value = "sort", required = false, defaultValue = "0") Integer sort,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {

		PageInfo pageInfo = new PageInfo(page, limit, QuestionSortEnum.typeOf(sort).getSort(),
				QuestionSortEnum.typeOf(sort).getOrder());

		BasePageDTO<CommentVO> basePageDTO = commentService.listByProductId(productId, StatusEnum.SHOW.getStatus(),
				pageInfo);
		model.addAttribute("commentVOs", basePageDTO.getList());// 评论列表
		model.addAttribute("pageInfo", basePageDTO.getPageInfo());// 分页信息

		return "/modules/comment/time_line_comment";
	}
	
	/**
	 * GET 商品评论页面
	 * @return
	 */
	@ApiOperation(value = "商品评论页面", notes = "商品评论页面")
	@GetMapping(value = "/list")
	public String list(Model model, @RequestParam(value = "productNumber", required = true) Long productNumber,
			@RequestParam(value = "sort", required = false, defaultValue = "1") String reqSort,
			@RequestParam(value = "page", required = false, defaultValue = "1") String reqPage,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit) {
		
		// 根据编号查找商品信息
		ProductVO product = productService.getByNumber(productNumber, StatusEnum.SHELVE.getStatus());
		
		if (product != null) {
			// 根据类目ID查找上级类目列表
			List<Category> upperCategories = categoryService.listUpperByProductId(product.getProductId(),
					StatusEnum.SHOW.getStatus());
			
			// 根据商品ID查找商品属性
			ProductAttribute productAttribute = productAttributeService.getByProductId(product.getProductId());

			// 请求参数:排序方式,如果排序方式不存在或者不为Integer类型,则默认1/最有帮助排序
			Integer sort = StringUtils.isNumeric(reqSort) ? Integer.valueOf(reqSort) : CommentSortEnum.HELP.getType();
			// 请求参数:分页,如果分页不存在或者不为Integer类型,则默认1/默认页数
			Integer page = StringUtils.isNumeric(reqPage) ? Integer.valueOf(reqPage) : 1;

			PageInfo pageInfo = new PageInfo(page, limit, QuestionSortEnum.typeOf(sort).getSort(),
					QuestionSortEnum.typeOf(sort).getOrder());

			BasePageDTO<CommentVO> basePageDTO = commentService.listByProductId(product.getProductId(),
					StatusEnum.SHOW.getStatus(), pageInfo);

			model.addAttribute("product", product);// 商品信息
			model.addAttribute("upperCategories", upperCategories);// 上级类目列表
			model.addAttribute("commentVOs", basePageDTO.getList());// 评论列表
			model.addAttribute("pageInfo", basePageDTO.getPageInfo());// 分页信息
			model.addAttribute("productAttribute", productAttribute);// 商品属性
			model.addAttribute("sort", CommentSortEnum.typeOf(sort).getType());// 排序方式

		}
		return "/modules/comment/comment_list";
	}
	
	/**
	 * PUT 点赞商品评价
	 * @return
	 */
	@ApiOperation(value = "点赞商品评价", notes = "点赞商品评价")
	@PutMapping(value = "/like")
	@ResponseBody
	public Object like(@RequestParam(value = "commentId", required = true) Long commentId) {
		AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
		if (authorizingUser != null) {
			// TODO 用户只要登录,就可以无限点赞,会不会导致恶意攻击
			Integer goodCount = commentService.updateLike(commentId);
			return new OsResult(CommonReturnCode.SUCCESS, goodCount);
		} else {
			return new OsResult(CommonReturnCode.UNAUTHORIZED);
		}
	}
	
	/**
	 * POST 评价回复
	 * @return
	 */
	@ApiOperation(value = "评价回复", notes = "评价回复")
	@PostMapping(value = "/reply")
	@ResponseBody
	public Object reply(@RequestParam(value = "commentId", required = true) Long commentId,
			@RequestParam(value = "content", required = true) String content) {
		AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
		if (authorizingUser != null) {
			UserVO user = userService.getById(authorizingUser.getUserId());
			
			CommentReply commentReply = new CommentReply();
			commentReply.setCommentId(commentId);
			commentReply.setContent(content);
			commentReply.setUserId(user.getUserId());
			commentReply.setPicImg(user.getPicImg());
			commentReply.setUserName(user.getUserName());
			Integer count = commentReplyService.insertCommentReply(commentReply);
			return new OsResult(CommonReturnCode.SUCCESS, count);
		} else {
			return new OsResult(CommonReturnCode.UNAUTHORIZED);
		}
	}
	
	/**
	 * PUT 点赞客服回复评价
	 * @return
	 */
	@ApiOperation(value = "点赞客服回复评价", notes = "点赞客服回复评价")
	@PutMapping(value = "/csLike")
	@ResponseBody
	public Object csLike(@RequestParam(value = "commentReplyId", required = true) Long commentReplyId) {
		AuthorizingUser authorizingUser = SingletonLoginUtils.getUser();
		if (authorizingUser != null) {
			// TODO 用户只要登录,就可以无限点赞,会不会导致恶意攻击
			Integer goodCount = commentReplyService.updateLike(commentReplyId);
			return new OsResult(CommonReturnCode.SUCCESS, goodCount);
		} else {
			return new OsResult(CommonReturnCode.UNAUTHORIZED);
		}
	}
}
