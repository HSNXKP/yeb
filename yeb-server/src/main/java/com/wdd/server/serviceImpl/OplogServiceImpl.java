package com.wdd.server.serviceImpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdd.server.mapper.OplogMapper;
import com.wdd.server.pojo.Oplog;
import com.wdd.server.service.OplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements OplogService {

}
