package org.pussinboots.ttui.product.service;

import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.product.entity.ProductCategory;
import org.pussinboots.ttui.product.pojo.vo.ProductVO;

import com.baomidou.mybatisplus.service.IService;

/**
 * 
* 项目名称：ttui-product-facade
* 类名称：IProductCategoryService   
* 类描述：ProductCategory / 商品分类关联表 业务逻辑层接口         
* 创建人：陈星星   
* 创建时间：2017年4月12日 下午4:21:36   
*
 */
public interface IProductCategoryService extends IService<ProductCategory> {
	
	/**
	 * 根据类目ID、排序、分页查找显示商品列表
	 * @param categoryId 类目ID
	 * @param pageInfo 分页实体
	 * @return
	 */
	BasePageDTO<ProductVO> listProducts(Long categoryId, PageInfo pageInfo);
	
	/**
	 * 根据搜索内容、排序、分页查找商品
	 * @param search 搜索内容
	 * @param pageInfo 排序、分页方式
	 * @return
	 */
	BasePageDTO<ProductVO> listBySearch(String search, PageInfo pageInfo);

	BasePageDTO<ProductVO> listByProductId(Long productId,String search, PageInfo pageInfo);

	ProductCategory selectByProductId(Long productId);

	Integer updateProductCategory(ProductCategory productCategory, String userName);
}
