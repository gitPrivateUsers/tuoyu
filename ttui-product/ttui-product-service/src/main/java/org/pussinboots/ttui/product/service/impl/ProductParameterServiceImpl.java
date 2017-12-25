package org.pussinboots.ttui.product.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import org.pussinboots.ttui.common.base.BasePageDTO;
import org.pussinboots.ttui.common.support.page.PageInfo;
import org.pussinboots.ttui.product.entity.ProductParameter;
import org.pussinboots.ttui.product.mapper.ProductParameterMapper;
import org.pussinboots.ttui.product.service.IProductParameterService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称：ttui-product-service
 * 类名称：ProductParameterServiceImpl
 * 类描述：ProductParameter / 商品参数表 业务逻辑层接口实现
 * 创建人：陈星星
 * 创建时间：2017年4月14日 上午2:07:35
 */
@Service
public class ProductParameterServiceImpl extends ServiceImpl<ProductParameterMapper, ProductParameter> implements IProductParameterService {

    @Autowired
    private ProductParameterMapper productParameterMapper;

    @Override
    public List<ProductParameter> listByProductId(Long productId, Integer status) {
        ProductParameter productParameter = new ProductParameter();
        productParameter.setProductId(productId);
        productParameter.setStatus(status);
        return productParameterMapper
                .selectList(new EntityWrapper<ProductParameter>(productParameter).orderBy("sort", true));
    }

    @Override
    public Integer updateProductParameter(ProductParameter productParameter, String userName) {
        if (productParameter.getProductParameterId() !=null) {
            productParameter.setUpdateBy(userName);
            productParameter.setUpdateTime(new Date());
            return productParameterMapper.updateById(productParameter);
        }
        productParameter.setCreateTime(new Date());
        productParameter.setCreateBy(userName);
        return productParameterMapper.insert(productParameter);

    }

    @Override
    public BasePageDTO<ProductParameter> listByPage(PageInfo pageInfo, String search, Long productId) {


        Page<ProductParameter> page = new Page<>(pageInfo.getCurrent(), pageInfo.getLimit());
        List<ProductParameter> products = productParameterMapper.listByPage(pageInfo, search, page, productId);
        pageInfo.setTotal(page.getTotal());
        return new BasePageDTO<ProductParameter>(pageInfo, products);
    }

    @Override
    public Integer insertProductParameter(ProductParameter productParameter, String userName) {
        productParameter.setCreateBy(userName);
        productParameter.setCreateTime(new Date());
        return productParameterMapper.insert(productParameter);
    }
}
