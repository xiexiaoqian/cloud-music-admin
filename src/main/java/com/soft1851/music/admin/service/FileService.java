package com.soft1851.music.admin.service;

import java.util.Map;

/**
 * @author xxq
 * @ClassName FileService
 * @Description TODO
 * @Date 2020/4/26
 * @Version 1.0
 **/
public interface FileService {
    /**
     * 设置导出歌单表单表头信息
     * @return
     */
    Map<String, String> exportSongList();
}