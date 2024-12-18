package com.devin.love.music.dao.v1;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.mapper.v1.AlbumMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2024/11/6 1:09
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class AlbumDao extends ServiceImpl<AlbumMapper, Album> {

    /**
     * 根据歌手id获取专辑列表
     *
     * @param singerIds
     * @return
     */
    public List<Album> getAlbumsBySingerIds(List<Long> singerIds) {
        return lambdaQuery()
                .in(Album::getSingerId, singerIds)
                .list();
    }

    /**
     * 根据歌手id删除专辑信息
     *
     * @param singerIds
     * @return
     */
    public boolean deleteBySingerIds(List<Long> singerIds) {
        return lambdaUpdate()
                .in(Album::getSingerId, singerIds)
                .remove();
    }

    /**
     * 获取所有专辑信息
     * @return
     */
    public List<Album> getAllAlbum() {
        return lambdaQuery().list();
    }
}
