package com.soft1851.music.admin.controller;

import com.soft1851.music.admin.service.FileService;
import com.soft1851.music.admin.service.SongListService;
import com.soft1851.music.admin.util.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xxq
 * @ClassName FileController
 * @Description TODO
 * @Date 2020/4/26
 * @Version 1.0
 **/
@RestController
@RequestMapping("/resources")
public class FileController {
    @Resource
    private SongListService songListService;
    @Resource
    private FileService fileService;

    @GetMapping("/songList")
    @ResponseBody
    public void export() throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletResponse response = attributes.getResponse();
        assert response != null;
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition","attachment");
        ExcelUtils.exportExcel(response, songListService.selectAll(), fileService.exportSongList(), "歌单表");
    }

}
