package org.pussinboots.ttui.product.mapper;

import org.apache.ibatis.annotations.Param;
import org.pussinboots.ttui.product.entity.ProductDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 
* 项目名称：ttui-product-service
* 类名称：ProductDetailMapper   
* 类描述：ProductDetail / 商品描述表 数据访问层接口         
* 创建人：陈星星   
* 创建时间：2017年4月14日 上午2:04:15   
*
 */
public interface ProductDetailMapper extends BaseMapper<ProductDetail> {

    ProductDetail selectByProductId(@Param("productId") Long productId);
}