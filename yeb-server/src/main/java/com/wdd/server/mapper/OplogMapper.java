package com.wdd.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wdd.server.pojo.Oplog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wdd
 * @since 2022-09-22
 */
@Mapper
public interface OplogMapper extends BaseMapper<Oplog> {

}
