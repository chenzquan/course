package com.course.business.controller.admin;


import com.course.server.domain.Chapter;

import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.service.ChapterService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    @Resource
    private ChapterService ChapterService;


    @PostMapping("/list")
    public PageDto Chapter(@RequestBody PageDto pageDto){
        ChapterService.list(pageDto);
        return pageDto;
    }

}
