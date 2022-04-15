package com.lowt.baabackend;

import com.lowt.baabackend.service.BaaBaamodelService;
import com.lowt.baabackend.service.BaaImgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@SpringBootTest
class BAABackendApplicationTests {

    @Autowired
    BaaImgService baaImgService;

    @Resource
    private RestTemplate restTemplate;
    @Autowired
    private BaaBaamodelService baaBaamodelService;

    @Test
    void test() {
//        final BaaImg baaImg = new BaaImg();
//        baaImg.setId(1L);
//        baaImg.setImgPath("Q:/Proj_Python/DataSet/Bone+Age+Training+Set/boneage-training-dataset/1385.png");
//        baaImgService.genPredictAge(baaImg);


//        Gson gson = new Gson();
//        String url = "http://" + "localhost" + ":" + "8000" + "/BAAModel/";
//        List<BaaModel> baaModels = new ArrayList<>();
//        String s = restTemplate.getForObject(url, String.class);
//        System.out.println(s);
//        FromBaaNetDTO<BaaModel> fromBaaNetDTO = gson.fromJson(s, FromBaaNetDTO.class);
//        System.out.println(fromBaaNetDTO);
//        System.out.println(baaModels);


//        System.out.println(baaBaamodelService.list());
        
    }
}
