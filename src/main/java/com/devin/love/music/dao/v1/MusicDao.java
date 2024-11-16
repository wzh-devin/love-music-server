package com.devin.love.music.dao.v1;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.love.music.domain.entity.Music;
import com.devin.love.music.mapper.v1.MusicMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

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
}
