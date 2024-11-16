package com.devin.love.music.service.v1.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devin.love.music.dao.v1.AlbumDao;
import com.devin.love.music.dao.v1.MusicDao;
import com.devin.love.music.dao.v1.SingerDao;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.resp.AlbumInfoResp;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;
import com.devin.love.music.service.v1.SingerService;
import com.devin.love.music.service.v1.builder.SingerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 2024/11/6 0:53
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SingerServiceImpl implements SingerService {

    private final SingerDao singerDao;
    private final AlbumDao albumDao;
    private final MusicDao musicDao;

    @Override
    public List<SingerInfoResp> getSingerList() {
        // 查询所有歌手
        List<Singer> singers = singerDao.getAllSingers();
        if (singers.isEmpty()) {
            // 如果歌手为空，直接返回
            return null;
        }

        // 查询歌手的所有专辑
        List<SingerInfoResp> singerInfoResps = new ArrayList<>();
        singers.forEach(singer -> {
            // 封装专辑信息
            List<AlbumInfoResp> albumInfoRespList = albumDao.getAlbumsBySingerId(singer.getId());
            albumInfoRespList.forEach(albumInfoResp -> {
                // 查询专辑中的歌曲
                Long musicTotal = musicDao.getMusicCount(singer.getId(), albumInfoResp.getId());
                albumInfoResp.setMusicTotal(musicTotal);
            });
            singerInfoResps.add(SingerBuilder.buildSingerInfoResp(singer, albumInfoRespList));
        });
        return singerInfoResps;
    }
}
