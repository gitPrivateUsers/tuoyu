package org.pussinboots.ttui.order.service.impl;

import org.pussinboots.ttui.order.entity.OrderStatus;
import org.pussinboots.ttui.order.mapper.OrderStatusMapper;
import org.pussinboots.ttui.order.service.IOrderStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
* 项目名称：ttui-order-service
* 类名称：OrderStatusServiceImpl   
* 类描述：OrderStatus / 订单状态表 业务逻辑层接口实现         
* 创建人：陈星星   
* 创建时间：2017年5月10日 上午10:34:34   
*
 */
@Service
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements IOrderStatusService {
	
}
