package com.lowt.baabackend.controller;


import com.lowt.baabackend.service.BaaPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin


public class BaaAnalyzeController {
    @Autowired
    BaaPersonService baaPersonService;
    

}
