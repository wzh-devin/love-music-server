package com.devin.love.music.service.v1;

import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.vo.resp.AlbumInfoResp;

import java.util.List;

/**
 * 2024/11/6 1:10
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface AlbumService {

    /**
     * 获取所有的专辑列表
     * @return
     */
    List<AlbumInfoResp> getAllAlbumList();

    /**
     * 获取歌手专辑列表
     * @param singerId
     * @return
     */
    List<AlbumInfoResp> getSingerAlbums(Long singerId);
}
