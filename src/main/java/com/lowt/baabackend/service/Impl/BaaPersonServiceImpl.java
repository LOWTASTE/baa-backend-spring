package com.lowt.baabackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowt.baabackend.entity.BaaPerson;
import com.lowt.baabackend.mapper.backend.BaaPersonMapper;
import com.lowt.baabackend.service.BaaPersonService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@Service
public class BaaPersonServiceImpl extends ServiceImpl<BaaPersonMapper, BaaPerson> implements BaaPersonService {

    BaaPersonMapper baaPersonMapper;

    @Cacheable(value = "BaaPerson", key = "'id:'+ #id", unless = "#result==null")
    public BaaPerson getByBaaPersonId(Serializable id) {
        return baseMapper.selectById(id);
    }

//    @CachePut(value = "BaaPerson", key = "'id:' + #baaPerson.getId()")
//    public BaaPerson updateByBaaPersonId()
//    @CacheEvict(value = "BaaPersonList", key = "'all'")

}
