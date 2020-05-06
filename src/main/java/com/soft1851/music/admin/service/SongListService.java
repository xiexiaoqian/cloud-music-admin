package com.soft1851.music.admin.service;

import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.domain.dto.PageDto;
import com.soft1851.music.admin.domain.entity.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xxq
 * @since 2020-04-22
 */
public interface SongListService extends IService<SongList> {
    /**
     * 分页查询所有歌单
     * @param pageDto
     * @return
     */
    ResponseResult searchSongList(PageDto pageDto);

    /**
     * 分页查询.
     * @param pageDto
     * @return
     */
    List<SongList> getByPage(PageDto pageDto);

    /**
     * 模糊查询
     * @param filed
     * @return
     */
    List<SongList> fuzzySearch(String filed);

    /**
     * 查询所有歌单
     * @return
     */
    List<Map<String, Object>> selectAll();

    /**
     * 根据id删除歌单
     * @param id
     * @return
     */
    ResponseResult deleteSongList(String id);

    /**
     *修改歌单数据
     * @param songList
     * @return
     */
    ResponseResult updateSongList(SongList songList);

    /**
     * 批量删除
     * @param idLists
     * @return
     */
    ResponseResult batchDeleteById(String idLists);

    /**
     * 热门歌单
     *
     * @return
     */
    List<SongList> getTopSongList();

    /**
     * 导出歌单
     */
    void exportData();
}
