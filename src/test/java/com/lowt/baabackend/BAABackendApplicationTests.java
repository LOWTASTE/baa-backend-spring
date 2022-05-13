package com.lowt.baabackend;

import com.lowt.baabackend.entity.BaaUsers;
import com.lowt.baabackend.mapper.backend.BaaUsersMapper;
import com.lowt.baabackend.service.BaaBaamodelService;
import com.lowt.baabackend.service.BaaImgService;
import com.lowt.baabackend.service.Impl.BaaPersonServiceImpl;
import com.lowt.baabackend.service.Impl.BaaUsersServiceImpl;
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

    @Autowired
    private BaaUsersServiceImpl baaUsersService;

    @Autowired
    private BaaUsersMapper baaUsersMapper;

//    @Autowired
//    private ESBaaPersonRepository esBaaPersonRepository;

    @Autowired
    private BaaPersonServiceImpl baaPersonService;


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

//        BaaUsers baaUsers = new BaaUsers();
//        baaUsers.setUsername("LOWT");
//        baaUsers.setPassword("5206");
//        int row = baaUsersMapper.insert(baaUsers);
//        if (row > 0) {
//            System.out.println("success");
//        } else {
//            System.out.println("fail");
//        }


// es 存入
//        esBaaPersonRepository.save(new ESBaaPerson().setName("OKOK"));

// 测试 insert_time 和 modification_time
//        BaaPerson baaPerson = new BaaPerson();
//        baaPerson.setName("WHAT");
//        baaPersonService.save(baaPerson);

        BaaUsers users = new BaaUsers();
        users.setPassword("OK-OK");
        users.setUsername("OK-OK");
        baaUsersService.save(users);

    }
}
