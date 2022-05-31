package com.lowt.baabackend.controller;

import com.lowt.baabackend.entity.BaaUsers;
import com.lowt.baabackend.service.Impl.BaaUsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/baabackend/baaUsers")
public class BaaUsersController {
    @Autowired
    BaaUsersServiceImpl baaUsersService;

    @Value("${headImg.upload-path}")
    private String headImgUploadPath;

    @Value("${headImg.upload-server-ip}")
    public String imgFileServerIp;

    @Value("${headImg.upload-server-port}")
    public String imgFileServerPort;

    @PostMapping("register")
    public ResponseEntity<BaaUsers> register(@RequestBody BaaUsers baaUsers) {
        try {
            BaaUsers users = baaUsersService.register(baaUsers);
            return ResponseEntity.ok(users);
        } catch (RuntimeException e) {
            System.out.println("register error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("info")
    public ResponseEntity<BaaUsers> userInfo(@RequestBody BaaUsers baaUsers) {
        try {
            return ResponseEntity.ok(baaUsersService.userInfoByName(baaUsers));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaaUsers> imgUpload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) throws IOException {

        if (file != null) {
            // 判断是否图片类型
            if (Objects.requireNonNull(file.getContentType()).contains("image") && file.getOriginalFilename() != null) {
                if (file.getSize() == 0) {
                    return ResponseEntity.badRequest().build();
                }
                File fileDir = new File(headImgUploadPath + id + '/');
                if (!fileDir.exists()) {
                    if (!fileDir.mkdirs()) {
                        return ResponseEntity.badRequest().build();
                    }
                }
                String[] suffixStr = file.getOriginalFilename().split("\\.");
                String suffix = suffixStr[suffixStr.length - 1].toLowerCase();
                String uuid = UUID.randomUUID().toString();
                String imgPath = headImgUploadPath + id + '/' + uuid + "." + suffix;
                File imgFile = new File(imgPath);
                file.transferTo(imgFile);
                String fileUrl = imgFileServerIp + ":" + imgFileServerPort + "/baabackend/headimg/" + id + "/" + uuid + "." + suffix;

                BaaUsers users = baaUsersService.getById(id);
                users.setUserHeadPic(fileUrl);
                baaUsersService.updateById(users);

                return ResponseEntity.ok(users);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
