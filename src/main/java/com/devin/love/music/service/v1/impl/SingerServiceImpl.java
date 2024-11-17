package com.devin.love.music.service.v1.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devin.love.music.common.utils.AssertUtil;
import com.devin.love.music.common.utils.SnowFlake;
import com.devin.love.music.dao.v1.AlbumDao;
import com.devin.love.music.dao.v1.MusicDao;
import com.devin.love.music.dao.v1.SingerDao;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Music;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.SingerReq;
import com.devin.love.music.domain.vo.resp.AlbumInfoResp;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;
import com.devin.love.music.service.v1.SingerService;
import com.devin.love.music.service.v1.builder.SingerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addSinger(SingerReq singerReq) {
        if (Objects.isNull(singerReq)) return;

        // 构建歌手实体
        Singer singer = SingerBuilder.buildSinger(singerReq);
        log.info("singer: {}", singer);
        boolean saveResult = singerDao.save(singer);
        AssertUtil.isTrue(saveResult, "歌手插入失败");

        // 判断专辑是否存在
        if (!singerReq.getAlbums().isEmpty()) {
            // 构建专辑列表，产生关联
            List<Album> albums = singerReq.getAlbums().stream()
                    .peek(album -> album.setSingerId(singer.getId()))
                    .toList();
            // 插入专辑
            boolean albumResult = albumDao.saveBatch(albums);
            AssertUtil.isTrue(albumResult, "专辑插入失败");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSingerInfo(SingerReq singerReq) {
        if (Objects.isNull(singerReq)) return;
        List<Album> albumList = singerReq.getAlbums();
        List<Long> albumIds = albumList.stream().map(Album::getId).distinct().toList();
        // 批量删除歌曲信息
        List<Music> musicList = musicDao.getMusicListByAlbumIdsOrSingerId(albumIds, singerReq.getId());
        if (!musicList.isEmpty()) {
            boolean delMusic = musicDao.removeByIds(musicList.stream().map(Music::getId).distinct().toList());
            AssertUtil.isTrue(delMusic, "歌曲删除失败");
        }
        // 批量删除专辑信息
        if (!albumIds.isEmpty()) {
            boolean delAlbum = albumDao.removeByIds(albumIds);
            AssertUtil.isTrue(delAlbum, "专辑删除失败");
        }
        // 删除歌手信息
        boolean delSinger = singerDao.removeById(singerReq.getId());
        AssertUtil.isTrue(delSinger, "歌手删除失败");
        // 新增歌手逻辑
        addSinger(singerReq);
    }
}
