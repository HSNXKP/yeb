package com.wdd.server.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdd.server.mapper.NationMapper;
import com.wdd.server.pojo.Nation;
import com.wdd.server.service.NationService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Service
public class NationServiceImpl extends ServiceImpl<NationMapper, Nation> implements NationService {

}
