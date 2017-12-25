package org.pussinboots.ttui.product.service.impl;

import org.pussinboots.ttui.product.entity.ProductAttribute;
import org.pussinboots.ttui.product.mapper.ProductAttributeMapper;
import org.pussinboots.ttui.product.service.IProductAttributeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：ttui-product-service
* 类名称：ProductAttributeServiceImpl   
* 类描述：ProductAttribute / 商品属性表 业务逻辑层接口实现      
* 创建人：陈星星   
* 创建时间：2017年4月14日 上午2:06:46   
*
 */
@Service
public class ProductAttributeServiceImpl extends ServiceImpl<ProductAttributeMapper, ProductAttribute> implements IProductAttributeService {
	
	@Autowired
	private ProductAttributeMapper productAttributeMapper;

	@Override
	public ProductAttribute getByProductId(Long productId) {
		ProductAttribute productAttribute = new ProductAttribute();
		productAttribute.setProductId(productId);
		return productAttributeMapper.selectOne(productAttribute);
	}
}
