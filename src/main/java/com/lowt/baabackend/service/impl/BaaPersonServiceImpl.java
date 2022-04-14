package com.lowt.baabackend.service.impl;

import com.lowt.baabackend.entity.BaaPerson;
import com.lowt.baabackend.mapper.BaaPersonMapper;
import com.lowt.baabackend.service.BaaPersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@Service
public class BaaPersonServiceImpl extends ServiceImpl<BaaPersonMapper, BaaPerson> implements BaaPersonService {

}
