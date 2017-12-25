package org.pussinboots.ttui.product.service.impl;

import java.util.Date;
import java.util.List;

import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.enums.StatusEnum;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.product.entity.ProductCategory;
import org.pussinboots.ttui.product.mapper.CategoryMapper;
import org.pussinboots.ttui.product.mapper.ProductCategoryMapper;
import org.pussinboots.ttui.product.pojo.vo.ProductVO;
import org.pussinboots.ttui.product.service.IProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * 
* 项目名称：ttui-product-service
* 类名称：ProductCategoryServiceImpl   
* 类描述：ProductCategory / 商品分类关联表 业务逻辑层接口实现         
* 创建人：陈星星   
* 创建时间：2017年4月12日 下午4:24:43   
*
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements IProductCategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ProductCategoryMapper productCategoryMapper;
	
	@Override
	public BasePageDTO<ProductVO> listProducts(Long categoryId, PageInfo pageInfo) {
		// 查询子目录信息ID
		List<Long> categoryIds = categoryMapper.listIds(categoryId, StatusEnum.SHOW.getStatus());

		// 查询该目录ID列表下商品列表
		Page<ProductVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
		List<ProductVO> productVOs = productCategoryMapper.listByPage(categoryIds, StatusEnum.SHELVE.getStatus(),
				pageInfo, page);
		pageInfo.setTotal(page.getTotal());

		return new BasePageDTO<ProductVO>(pageInfo, productVOs);
	}

	@Override
	public BasePageDTO<ProductVO> listBySearch(String search, PageInfo pageInfo) {
		Page<ProductVO> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
		List<ProductVO> productVOs = productCategoryMapper.listBySearch(search, StatusEnum.SHELVE.getStatus(), pageInfo,
				page);
		pageInfo.setTotal(page.getTotal());

		return new BasePageDTO<ProductVO>(pageInfo, productVOs);
	}

	@Override
	public BasePageDTO<ProductVO> listByProductId(Long productId,String seerch, PageInfo pageInfo) {
		return null;
	}

	@Override
	public ProductCategory selectByProductId(Long productId) {
		return productCategoryMapper.selectByProductId(productId);
	}

	@Override
	public Integer updateProductCategory(ProductCategory productCategory, String userName) {

		if (productCategory.getProductCategoryId() !=null) {
			return productCategoryMapper.updateById(productCategory);
		}
		productCategory.setCreateTime(new Date());
		productCategory.setCreateBy(userName);
		return productCategoryMapper.insert(productCategory);

	}
}
