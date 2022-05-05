package com.lowt.baabackend.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lowt.baabackend.dto.FromPredictAPIDTO;
import com.lowt.baabackend.dto.ToPredictAPIDTO;
import com.lowt.baabackend.entity.BaaBaamodel;
import com.lowt.baabackend.entity.BaaImg;
import com.lowt.baabackend.entity.BaaPredictAge;
import com.lowt.baabackend.mapper.backend.BaaImgMapper;
import com.lowt.baabackend.service.BaaBaamodelService;
import com.lowt.baabackend.service.BaaImgService;
import com.lowt.baabackend.service.BaaPredictAgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

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

    @Autowired
    private BaaBaamodelService baaBaamodelService;

    @Autowired
    private BaaPredictAgeService baaPredictAgeService;

    @Async
    @Override
    public void genPredictAge(BaaImg baaImg) {
        System.out.println("start-predict");
        // 获取图片地址
        final String imgPath = baaImg.getImgPath();
        // 获取model-id
        List<BaaBaamodel> baaBaamodels = baaBaamodelService.list();
        if (baaBaamodels.size() != 0) {
            for (BaaBaamodel baaBaamodel : baaBaamodels) {

                ToPredictAPIDTO toPredictAPIDTO = new ToPredictAPIDTO(imgPath, baaBaamodel.getId());
                ResponseEntity<FromPredictAPIDTO> responseEntity = postForGenPredictAge(toPredictAPIDTO);
                FromPredictAPIDTO fromPredictAPIDTO = responseEntity.getBody();
                if (fromPredictAPIDTO != null) {
                    BaaPredictAge baaPredictAge = new BaaPredictAge();
                    baaPredictAge.setAge(fromPredictAPIDTO.getPredict_age().doubleValue());
                    baaPredictAge.setModelId(baaBaamodel.getId());
                    baaPredictAge.setImgId(baaImg.getId());
                    baaPredictAgeService.save(baaPredictAge);
                    System.out.println(baaBaamodel.getName() + " Model" + " predict done");
                    continue;
                }
                System.out.println("************");
            }
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
