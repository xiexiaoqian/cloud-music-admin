package com.soft1851.music.admin.mapper;

import com.soft1851.music.admin.dto.PageDto;
import com.soft1851.music.admin.entity.SongList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xxq
 * @since 2020-04-22
 */
public interface SongListMapper extends BaseMapper<SongList> {
    /***
     * 模糊查询+分页查询
     * @param pageDto
     * @return
     * @throws SQLException
     */
    @Select("SELECT * FROM song_list " +
            "WHERE song_list_name LIKE CONCAT('%',#{pageDto.field},'%') OR type LIKE CONCAT('%',#{pageDto.field},'%') " +
            "LIMIT ${pageDto.pageSize*(pageDto.currentPage-1)},#{pageDto.pageSize}")
    List<SongList> searchSongList(@Param("pageDto") PageDto pageDto) throws SQLException;
}
