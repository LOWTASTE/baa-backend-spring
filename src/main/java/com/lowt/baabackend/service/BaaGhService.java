package com.lowt.baabackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lowt.baabackend.entity.BaaGh;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
public interface BaaGhService extends IService<BaaGh> {
    List<BaaGh> personGhList(Long pId);
}