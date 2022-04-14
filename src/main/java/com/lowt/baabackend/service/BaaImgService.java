package com.lowt.baabackend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lowt.baabackend.entity.BaaImg;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
public interface BaaImgService extends IService<BaaImg> {
    void genPredictAge(BaaImg baaImg);
}
