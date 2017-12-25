package org.pussinboots.ttui.product.service.impl;

import org.pussinboots.ttui.product.entity.Specification;
import org.pussinboots.ttui.product.mapper.SpecificationMapper;
import org.pussinboots.ttui.product.service.ISpecificationService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：ttui-product-service
* 类名称：SpecificationServiceImpl   
* 类描述：Specification / 规格表 业务逻辑层接口实现              
* 创建人：陈星星   
* 创建时间：2017年4月14日 上午2:08:37   
*
 */
@Service
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements ISpecificationService {
	
}
