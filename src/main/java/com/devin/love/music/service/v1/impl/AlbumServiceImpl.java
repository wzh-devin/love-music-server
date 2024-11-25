package com.devin.love.music.service.v1.impl;

import com.devin.love.music.common.utils.AssertUtil;
import com.devin.love.music.dao.v1.AlbumDao;
import com.devin.love.music.dao.v1.MusicDao;
import com.devin.love.music.dao.v1.SingerDao;
import com.devin.love.music.domain.dto.AlbumInfoDto;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.service.v1.AlbumService;
import com.devin.love.music.service.v1.builder.SingerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 2024/11/6 1:10
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final AlbumDao albumDao;
    private final SingerDao singerDao;
    private final MusicDao musicDao;

    @Override
    public List<AlbumInfoDto> getAllAlbumList() {
//         List<Album> albumList = albumDao.getAllAlbum();
         // 封装专辑信息
        return null;
    }

    @Override
    public List<AlbumInfoDto> getSingerAlbums(Long singerId) {
        // 查询歌手信息
        Singer singer = singerDao.getById(singerId);
        List<Album> albumList = albumDao.getAlbumsBySingerIds(List.of(singerId));
        // 封装专辑信息
        return albumList.stream().map(album -> {
            AlbumInfoDto albumInfoDto = SingerBuilder.buildAlbumInfoResp(album, singer);
            // 查询歌曲总数
            Long musicTotal = musicDao.getMusicCount(singerId, album.getId());
            albumInfoDto.setMusicTotal(musicTotal);
            return albumInfoDto;
        }).toList();
    }

    @Override
    public void addAlbum(AlbumInfoDto albumInfoDto) {
        if (Objects.isNull(albumInfoDto)) return;
        Album album = new Album();
        BeanUtils.copyProperties(albumInfoDto, album);
        boolean saveAlbumResult = albumDao.save(album);
        AssertUtil.isTrue(saveAlbumResult, "新增专辑失败");
    }
}
