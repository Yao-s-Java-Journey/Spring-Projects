package com.manager.controller;

import com.manager.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {
    /**
     * 本地图片上传
     * @param file
     * @return
     */
    @PostMapping("/localPic")
    public Result uploadLocalPic(MultipartFile file) throws IOException {
        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2. 通过 uuid 生成随机字符串（避免重名覆盖）
        // 2.1 分割文件名和扩展名
        int lastDotIndex = originalFilename.lastIndexOf('.');
        String prefix = originalFilename.substring(0, lastDotIndex); // 前缀
        String extension = originalFilename.substring(lastDotIndex); // 扩展名
        // 2.2 在中间拼接上 uuid
        StringBuffer sb = new StringBuffer(prefix);
        sb.append("_").append(UUID.randomUUID()).append(extension);
        String fileName = sb.toString();


        // 3. 将前端上传的文件存到本地
        file.transferTo(new File("D:/" + fileName));

        return Result.success(fileName);
    }
}
