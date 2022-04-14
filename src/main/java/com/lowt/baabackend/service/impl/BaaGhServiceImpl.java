package com.lowt.baabackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lowt.baabackend.entity.BaaGh;
import com.lowt.baabackend.mapper.BaaGhMapper;
import com.lowt.baabackend.service.BaaGhService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@Service
public class BaaGhServiceImpl extends ServiceImpl<BaaGhMapper, BaaGh> implements BaaGhService {

    @Override
    public List<BaaGh> personGhList(Long pId){
        return baseMapper.selectList(new QueryWrapper<BaaGh>().eq("p_id",pId));
    }
}
