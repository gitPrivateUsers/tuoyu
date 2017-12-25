package org.pussinboots.ttui.product.service;

import org.pussinboots.ttui.product.entity.ProductDetail;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
* 项目名称：ttui-product-facade
* 类名称：IProductDetailService   
* 类描述：ProductDetail / 商品描述表 业务逻辑层接口            
* 创建人：陈星星   
* 创建时间：2017年4月14日 上午2:01:49   
*
 */
public interface IProductDetailService extends IService<ProductDetail> {

    Integer updateProductDetail(ProductDetail productDetail, String userName);

    ProductDetail selectByProductId(Long productId);
}
