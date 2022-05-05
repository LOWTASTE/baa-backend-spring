package com.lowt.baabackend.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lowt.baabackend.entity.BaaPerson;
import com.lowt.baabackend.service.BaaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/baaAnalyze")
public class BaaAnalyzeController {
    @Autowired
    private BaaPersonService baaPersonService;

    @GetMapping("/count/{indication}")
    private Integer countForIndication(@PathVariable String indication) {
        return baaPersonService.count(new QueryWrapper<BaaPerson>().eq("indications", indication));
    }
}
