package org.example.tlias.controller;

import jakarta.servlet.annotation.MultipartConfig;
import lombok.extern.slf4j.Slf4j;
import org.example.tlias.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.example.tlias.utils.AliOSSUtils;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    // 本地存储文件方式
//    @PostMapping("/upload")
//    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
//        log.info("文件上传：{}， {}， {}", username, age, image);
//
//        //构造唯一的文件名---uuid（通用唯一识别码）
//        String originalFileName = image.getOriginalFilename();
//        int index = originalFileName.lastIndexOf('.');
//        String extname = originalFileName.substring(index);
//        String newFileName = UUID.randomUUID().toString() + extname;
//        image.transferTo(new File("E:\\" + newFileName));
//        return Result.success();
//    }


    // 阿里云oss存储方式
    @Autowired
    private AliOSSUtils aliOSSUtils;
    @PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
        log.info("文件上传，文件名{}", image.getOriginalFilename());
        String url = aliOSSUtils.upload(image);
        log.info("文件上传成功，文件访问url：{}", url);
        return Result.success(url);
    }
}
