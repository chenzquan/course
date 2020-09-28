package com.course.file.controller.admin;

import com.course.server.dto.FileDto;
import com.course.server.dto.ResponseDto;
import com.course.server.enums.FileUseEnum;
import com.course.server.service.FileService;
import com.course.server.util.Base64ToMultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


@RequestMapping("/admin")
@RestController
public class UploadController {

    private static final Logger LOG = LoggerFactory.getLogger(UploadController.class);

    public static final String BUSINESS_NAME = "文件上传";

    @Value("${file.domain}")
    private String FILE_DOMAIN;

    @Value("${file.path}")
    private String FILE_PATH;

    @Resource
    private FileService fileService;



    @PostMapping("/upload")
    public ResponseDto upload(@RequestBody FileDto fileDto) throws IOException {

//        LOG.info(shard.getOriginalFilename());
//        LOG.info(String.valueOf(shard.getSize()));

        String use = fileDto.getUse();
        String key = fileDto.getKey();
        String suffix = fileDto.getSuffix();
        String shardBase64 = fileDto.getShard();

        MultipartFile shard = Base64ToMultipartFile.base64ToMultipart(shardBase64);

        FileUseEnum useEnum = FileUseEnum.getByCode(use);


//        String fileName = shard.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

        String dir = useEnum.name().toUpperCase();
        File fullDir = new File(FILE_PATH + dir);
        if(!fullDir.exists()){
            fullDir.mkdir();
        }


        String path = dir + File.separator + key + "." + suffix;

        String fullPath = FILE_PATH + path;
        File dest = new File(fullPath);
        shard.transferTo(dest);

        LOG.info(dest.getAbsolutePath());
        LOG.info("保存文件记录开始");

//        FileDto fileDto = new FileDto();
        fileDto.setPath(path);
        fileService.save(fileDto);

        ResponseDto responseDto = new ResponseDto();
        fileDto.setPath(FILE_DOMAIN + path);
        responseDto.setContent(fileDto);
        return responseDto;
    }





}
