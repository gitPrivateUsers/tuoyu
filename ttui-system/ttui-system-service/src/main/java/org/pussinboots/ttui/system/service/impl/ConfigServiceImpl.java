package org.pussinboots.ttui.system.service.impl;

import org.pussinboots.ttui.system.entity.Config;
import org.pussinboots.ttui.system.mapper.ConfigMapper;
import org.pussinboots.ttui.system.service.IConfigService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 *
 * 项目名称：ttui-system-facade
 * 类名称：Config
 * 类描述：Config / 系统配置表 业务逻辑层接口实现
 * 创建人：陈星星
 * 创建时间：2017年7月10日 上午10:08:49
 *
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {
	
}
