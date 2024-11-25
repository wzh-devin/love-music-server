package com.devin.love.music.service.v1.impl;

import com.devin.love.music.common.enums.ExecuteStatusEnum;
import com.devin.love.music.common.utils.AssertUtil;
import com.devin.love.music.common.utils.SnowFlake;
import com.devin.love.music.dao.v1.AlbumDao;
import com.devin.love.music.dao.v1.MusicDao;
import com.devin.love.music.dao.v1.SingerDao;
import com.devin.love.music.domain.dto.AlbumInfoDto;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.AlbumReq;
import com.devin.love.music.service.v1.AlbumService;
import com.devin.love.music.service.v1.builder.SingerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addAlbum(AlbumInfoDto albumInfoDto) {
        addCommon(albumInfoDto, ExecuteStatusEnum.ADD);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editAlbum(AlbumInfoDto albumInfoDto) {
        deleteCommon(List.of(albumInfoDto.getId()), albumInfoDto.getSingerId());
        addCommon(albumInfoDto, ExecuteStatusEnum.UPDATE);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delAlbum(AlbumReq albumReq) {
        deleteCommon(albumReq.getAlbumIds(), albumReq.getSingerId());
    }

    /**
     * 公共删除方法
     * @param albumsIds
     */
    private void deleteCommon(List<Long> albumsIds, Long singerId) {
        if (!musicDao.getMusicByAlbumIds(albumsIds).isEmpty()) {
            // 删除专辑下所有的歌曲
            boolean deleteMusicResult = musicDao.deleteBySingerIdsAndSingerId(albumsIds, singerId);
            AssertUtil.isTrue(deleteMusicResult, "删除歌曲失败");
        }
        // 删除专辑
        boolean deleteAlbumResult = albumDao.removeBatchByIds(albumsIds);
        AssertUtil.isTrue(deleteAlbumResult, "删除专辑失败");
    }

    /**
     * 公共新增方法
     * @param albumInfoDto
     * @param executeStatus 执行状态
     */
    private void addCommon(AlbumInfoDto albumInfoDto, ExecuteStatusEnum executeStatus) {
        if (Objects.isNull(albumInfoDto)) return;

        Album album = new Album();
        BeanUtils.copyProperties(albumInfoDto, album);

        // 如果新增，则生成新的id
        if (ExecuteStatusEnum.ADD.name().equals(executeStatus.name())) {
            album.setId(SnowFlake.nextId());
        }

        boolean saveAlbumResult = albumDao.save(album);
        AssertUtil.isTrue(saveAlbumResult, "新增专辑失败");
    }
}
