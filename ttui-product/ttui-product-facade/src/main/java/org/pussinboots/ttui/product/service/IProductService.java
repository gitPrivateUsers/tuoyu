package org.pussinboots.ttui.product.service;

import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.product.entity.Product;
import org.pussinboots.ttui.product.pojo.vo.ProductVO;

import com.baomidou.mybatisplus.service.IService;

/**
 * 
* 项目名称：ttui-product-facade
* 类名称：IProductService   
* 类描述：Product / 商品表 业务逻辑层接口          
* 创建人：陈星星   
* 创建时间：2017年4月11日 下午3:13:06   
*
 */
public interface IProductService extends IService<Product> {
	
	/**
	 * 根据商品编号和状态查找商品
	 * @param productNumber 商品编号
	 * @param status 状态
	 * @return
	 */
	ProductVO getByNumber(Long productNumber, Integer status);

	BasePageDTO<Product> listByPage(PageInfo pageInfo, String search);

	Integer insertProduct(Product product, String userName);

	/**
	 * 根据id及信息修改商品
	 * @param product
	 * @param userName
     * @return
     */
	Integer updateProduct(Product product, String userName);
}
