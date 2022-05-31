package com.lowt.baabackend.controller;


import com.lowt.baabackend.entity.BaaImg;
import com.lowt.baabackend.service.BaaImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@CrossOrigin
@RestController
@RequestMapping("/baabackend/baaImg")
public class BaaImgController {

    @Value("${img.upload-path}")
    private String webUploadPath;

    @Value("${img.upload-server-ip}")
    public String imgFileServerIp;

    @Value("${img.upload-server-port}")
    public String imgFileServerPort;

    @Autowired
    private BaaImgService baaImgService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaaImg> imgUpload(@RequestParam("file") MultipartFile file, @RequestParam("pId") Long pId) throws IOException {
        System.out.println(pId);
        if (file != null) {
            // 判断是否图片类型
            if (Objects.requireNonNull(file.getContentType()).contains("image") && file.getOriginalFilename() != null) {
                if (file.getSize() == 0) {
                    return ResponseEntity.badRequest().build();
                }
                File fileDir = new File(webUploadPath + pId + '/');
                if (!fileDir.exists()) {
                    if (!fileDir.mkdirs()) {
                        return ResponseEntity.badRequest().build();
                    }
                }
                String[] suffixStr = file.getOriginalFilename().split("\\.");
                String suffix = suffixStr[suffixStr.length - 1].toLowerCase();
                String uuid = UUID.randomUUID().toString();
                String imgPath = webUploadPath + pId + '/' + uuid + "." + suffix;
                File imgFile = new File(imgPath);
                file.transferTo(imgFile);
                String fileUrl = imgFileServerIp + ":" + imgFileServerPort + "/baabackend/img/" + pId + "/" + uuid + "." + suffix;

                final BaaImg baaImg = new BaaImg();
                baaImg.setPId(pId);
                baaImg.setImgPath(fileUrl);
                baaImgService.save(baaImg);
                // 预测年龄
//                baaImgService.genPredictAge(baaImg);
                return ResponseEntity.ok(baaImg);
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("list")
    public ResponseEntity<List<BaaImg>> baaImgList() {
        try {
            return ResponseEntity.ok(baaImgService.list());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

