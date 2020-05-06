package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soft1851.music.admin.common.ResponseResult;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SongListMapper;
import com.soft1851.music.admin.service.SongListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xxq
 * @since 2020-04-22
 */
@Service
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {

    @Resource
    private SongListMapper songListMapper;


    @Override
    public ResponseResult searchSongList(PageDto pageDto) {
        List<SongList> mapList;
        try {
            mapList = songListMapper.searchSongList(pageDto);
        } catch (SQLException e) {
            return ResponseResult.failure(ResultCode.DATABASE_ERROR);
        }
        return ResponseResult.success(mapList);
    }

    @Override
    public List<SongList> getByPage(PageDto pageDto) {
        Page<SongList> page = new Page<>(pageDto.getCurrentPage(),pageDto.getPageSize());
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        IPage<SongList> iPage = songListMapper.selectPage(page,wrapper);
        return iPage.getRecords();
    }

    @Override
    public List<SongList> fuzzySearch(String filed) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("song_list_name",filed)
                .or().like("type",filed);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> selectAll() {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.select("song_list_id", "song_list_name", "thumbnail", "play_counts", "type", "song_count", "create_time")
                .orderByDesc("play_counts");
        List<Map<String, Object>> songLists = songListMapper.selectMaps(wrapper);
        if(songLists != null){
            return songLists;
        }
        throw new CustomException("歌单查询异常", ResultCode.DATABASE_ERROR);
    }

    /**
     * 根绝歌单id删除歌单
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteSongList(String id) {
        songListMapper.deleteById(id);
        return ResponseResult.success();
    }

    @Override
    public ResponseResult updateSongList(SongList songList) {
        SongList songList1 = songListMapper.selectById(songList.getSongListId());
        songList1.setSongListName(songList.getSongListName());
        songList1.setThumbnail(songList.getThumbnail());
        songList1.setType(songList.getType());
        songListMapper.updateById(songList1);
        return ResponseResult.success(songList1);
    }

    /**
     * 批量删除
     * @param idLists
     * @return
     */
    @Override
    public ResponseResult batchDeleteById(String idLists) {
        List<String> allIdList = new ArrayList<>();
        String[] ids = idLists.split(",");
        List<String> allIds = new ArrayList<>();
        for (int i = 0;i<ids.length;i++){
            allIds.add(ids[i]);
        }
        songListMapper.deleteBatchIds(allIds);
        return ResponseResult.success();
    }
}