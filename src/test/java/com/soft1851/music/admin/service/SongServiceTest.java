package com.soft1851.music.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.music.admin.entity.Song;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SongServiceTest {
    @Resource
    private SongService songService;

    @Test
    void count() {
        QueryWrapper<Song> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("singer", "黄");
        int count = songService.count(queryWrapper);
        System.out.println(count);
    }
}