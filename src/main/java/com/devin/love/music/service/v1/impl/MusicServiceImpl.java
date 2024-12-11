package com.devin.love.music.service.v1.impl;

import com.devin.love.music.dao.v1.AlbumDao;
import com.devin.love.music.dao.v1.MusicDao;
import com.devin.love.music.dao.v1.SingerDao;
import com.devin.love.music.domain.dto.MusicDto;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Music;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.service.v1.MusicService;
import com.devin.love.music.service.v1.builder.MusicBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 2024/11/7 1:56
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class MusicServiceImpl implements MusicService {

    public static final long ALBUM_NOT_EXIST = -1L;
    private final MusicDao musicDao;
    private final SingerDao singerDao;
    private final AlbumDao albumDao;

    @Override
    public List<MusicDto> getMusicList(Long singerId, Long albumId) {
        if (Objects.isNull(singerId)) return null;

        List<Music> musicList;
        Singer singer = singerDao.getById(singerId);
        Album album;

        if (albumId == ALBUM_NOT_EXIST) {
            album = null;
            musicList = musicDao.getMusicBySingerIds(List.of(singerId));
        } else {
            album = albumDao.getById(albumId);
            musicList = musicDao.getMusicByAlbumIds(List.of(albumId));
        }

        return musicList.stream().map(music ->MusicBuilder.buildMusicDto(music, singer, album)).toList();
    }
}
