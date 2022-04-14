package com.lowt.baabackend;

import com.google.gson.Gson;
import com.lowt.baabackend.dto.FromBaaNetDTO;
import com.lowt.baabackend.entity.BaaModel;
import com.lowt.baabackend.service.BaaImgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BAABackendApplicationTests {

    @Autowired
    BaaImgService baaImgService;

    @Resource
    private RestTemplate restTemplate;

    @Test
    void test() {
//        final BaaImg baaImg = new BaaImg();
//        baaImg.setId(1L);
//        baaImg.setImgPath("Q:/Proj_Python/DataSet/Bone+Age+Training+Set/boneage-training-dataset/1385.png");
//        baaImgService.genPredictAge(baaImg);


//        String url = "http://" + "localhost" + ":" + "8000";
//        Map<String, Object> map = new HashMap<>();
////        System.out.println(restTemplate.getForObject(url, List.class, map));
//
//        WebClient webClient = WebClient.builder()
//                .baseUrl(url)
//                .build();
//        try {
//            Mono<List> response = webClient.method(HttpMethod.GET)
//                    .uri("/BAAModel/")
//                    .retrieve()
//                    .bodyToMono(List.class);
//            System.out.println(response);
//            List<BaaModel> list = response.block();
//            System.out.println(list);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        Gson gson = new Gson();

        String url = "http://" + "localhost" + ":" + "8000" + "/BAAModel/";
        List<BaaModel> baaModels = new ArrayList<>();
        String s = restTemplate.getForObject(url, String.class);
        System.out.println(s);
        FromBaaNetDTO<BaaModel> fromBaaNetDTO = gson.fromJson(s, FromBaaNetDTO.class);
        System.out.println(fromBaaNetDTO);

        System.out.println(baaModels);

    }
}
