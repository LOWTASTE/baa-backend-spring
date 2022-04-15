package com.lowt.baabackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowt.baabackend.constants.Constants;
import com.lowt.baabackend.dto.FromPredictAPIDTO;
import com.lowt.baabackend.dto.ToPredictAPIDTO;
import com.lowt.baabackend.entity.BaaImg;
import com.lowt.baabackend.mapper.backend.BaaImgMapper;
import com.lowt.baabackend.service.BaaImgService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@Service
public class BaaImgServiceImpl extends ServiceImpl<BaaImgMapper, BaaImg> implements BaaImgService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${baa.net.api.host}")
    private String baaNetApiHost;

    @Value("${baa.net.api.port}")
    private String baaNetApiPort;


    @Async
    @Override
    public void genPredictAge(BaaImg baaImg) {
        final String imgPath = baaImg.getImgPath();
        final ToPredictAPIDTO toPredictAPIDTO = new ToPredictAPIDTO(imgPath, Constants.DEFAUlT_MODEL_ID);
        System.out.println("start");
        ResponseEntity<FromPredictAPIDTO> responseEntity = postForGenPredictAge(toPredictAPIDTO);
        FromPredictAPIDTO fromPredictAPIDTO = responseEntity.getBody();
        if (fromPredictAPIDTO != null) {
            baaImg.setPredictId(fromPredictAPIDTO.getPredict_age().intValue());
            baseMapper.updateById(baaImg);
            System.out.println("finish");
        }
    }

    // 取得预测年龄
    private ResponseEntity<FromPredictAPIDTO> postForGenPredictAge(ToPredictAPIDTO content) {
        String url = "http://" + baaNetApiHost + ":" + baaNetApiPort + "/PredictAPI/";
        HttpHeaders headers = new HttpHeaders();
        //将Post的body和header塞进HttpEntity
        HttpEntity<ToPredictAPIDTO> httpEntity = new HttpEntity<ToPredictAPIDTO>(content, headers);
        return restTemplate.postForEntity(url, httpEntity, FromPredictAPIDTO.class);
    }

}
