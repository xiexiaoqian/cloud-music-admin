package com.soft1851.music.admin.controller;


import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.soft1851.music.admin.service.SongListService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xxq
 * @since 2020-04-22
 */
@RestController
@Slf4j
@RequestMapping("/songList")
public class SongListController {
    @Resource
    private SongListService songListService;

//    /**
//     * 分页查询歌单数据
//     *
//     * @param pageDto
//     * @return
//     */
//    @PostMapping("/blur/page")
//    public ResponseResult getAllSongList(@RequestBody PageDto pageDto) {
//        return songListService.searchSongList(pageDto);
//    }

    /**
     * 分页查询歌单数据
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/page")
    public List<SongList> getSongListByPage(@RequestBody PageDto pageDto) {
        return songListService.getByPage(pageDto);
    }

    /**
     * 根据关键字模糊查询
     *
     * @param field
     * @return
     */
    @GetMapping("/blur")
    public List<SongList> fuzzySearch(@Param("field") String field) {
        return songListService.fuzzySearch(field);
    }

    /**
     * 根绝歌单id删除歌单数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    ResponseResult deleteSongListById(@Param("id") String id) {
        return songListService.deleteSongList(id);
    }

    /**
     * 修改歌单信息
     *
     * @param songList
     * @return
     */
    @PutMapping("/update")
    ResponseResult updateSongList(@RequestBody SongList songList) {
        return songListService.updateSongList(songList);
    }

    @DeleteMapping("/batchDelete")
    ResponseResult batchDeleteById(@Param("ids") String ids) {
        return songListService.batchDeleteById(ids);
    }

}