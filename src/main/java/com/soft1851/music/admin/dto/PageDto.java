package com.soft1851.music.admin.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author xxq
 * @ClassName PageDto
 * @Description TODO
 * @Date 2020/4/26
 * @Version 1.0
 **/
@Data
@Builder
public class PageDto {
    private Object field;
    private int currentPage;
    private int pageSize;
}
