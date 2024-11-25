package com.devin.love.music.service.v1;

import com.devin.love.music.domain.dto.MusicDto;

import javax.validation.Valid;
import java.util.List;

/**
 * 2024/11/7 1:55
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface MusicService {

    /**
     * 查询音乐列表
     * @param singerId
     * @param albumId
     * @return
     */
    List<MusicDto> getMusicList(Long singerId, Long albumId);
}
