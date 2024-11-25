package com.devin.love.music.dao.v1;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.love.music.common.utils.AssertUtil;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Music;
import com.devin.love.music.mapper.v1.MusicMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2024/11/7 1:56
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class MusicDao extends ServiceImpl<MusicMapper, Music> {

    /**
     * 获取歌曲数量
     *
     * @param singerId
     * @param albumId
     * @return
     */
    public Long getMusicCount(Long singerId, Long albumId) {
        return lambdaQuery()
                .eq(Music::getSingerId, singerId)
                .eq(Music::getAlbumId, albumId)
                .count();
    }

    /**
     * 根据专辑id或者歌手id获取歌曲
     * @param albumIds
     * @param singerIds
     * @return
     */
    public List<Music> getMusicListByAlbumIdsOrSingerIds(List<Long> albumIds, List<Long> singerIds) {
        if (albumIds.isEmpty()) {
            return lambdaQuery()
                    .in(Music::getSingerId, singerIds)
                    .list();
        }
        return lambdaQuery()
                .in(Music::getSingerId, singerIds)
                .or()
                .in(Music::getAlbumId, albumIds).list();
    }

    /**
     * 根据歌手id删除歌曲信息
     * @param singerIds
     * @return
     */
    public boolean deleteBySingerIds(List<Long> singerIds) {
        return lambdaUpdate()
                .in(Music::getSingerId, singerIds)
                .remove();
    }

    /**
     * 根据歌手id获取歌曲
     * @param singerIds
     * @return
     */
    public List<Music> getMusicBySingerIds(List<Long> singerIds) {
        return lambdaQuery()
                .in(Music::getSingerId, singerIds)
                .list();
    }

    /**
     * 根据专辑ids和歌手id删除歌曲
     * @param albumsIds
     * @param singerId
     * @return
     */
    public boolean deleteBySingerIdsAndSingerId(List<Long> albumsIds, Long singerId) {
        return lambdaUpdate()
                .in(Music::getAlbumId, albumsIds)
                .eq(Music::getSingerId, singerId)
                .remove();
    }

    /**
     * 根据专辑id获取歌曲
     * @param albumsIds
     * @return
     */
    public List<Music> getMusicByAlbumIds(List<Long> albumsIds) {
        return lambdaQuery()
                .in(Music::getAlbumId, albumsIds)
                .list();
    }
}
