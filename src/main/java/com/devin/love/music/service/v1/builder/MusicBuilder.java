package com.devin.love.music.service.v1.builder;

import com.devin.love.music.domain.dto.MusicDto;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Music;
import com.devin.love.music.domain.entity.Singer;
import org.springframework.beans.BeanUtils;

import java.util.Objects;

/**
 * 2024/11/26 0:05
 * <p></p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class MusicBuilder {

    /**
     * 构建MusicDto对象
     * @param music
     * @return
     */
    public static MusicDto buildMusicDto(Music music, Singer singer, Album album) {
        MusicDto musicDto = new MusicDto();
        BeanUtils.copyProperties(music, musicDto);
        musicDto.setSingerId(singer.getId());
        musicDto.setSingerName(singer.getName());
        if (!Objects.isNull(album)) {
            musicDto.setAlbumId(album.getId());
            musicDto.setAlbumName(album.getName());
        }
        return musicDto;
    }
}
