package com.devin.love.music.service.v1.builder;

import com.devin.love.music.domain.dto.AlbumInfoDto;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.SingerReq;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;
import org.springframework.beans.BeanUtils;

/**
 * 2024/11/6 1:37
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class SingerBuilder {

    public static SingerInfoResp buildSingerInfoResp(Singer singer) {
        return SingerInfoResp.builder()
                    .id(singer.getId())
                    .sex(singer.getSex())
                    .name(singer.getName())
                    .description(singer.getDescription())
                    .singerPicUrl(singer.getSingerPicUrl())
                    .birthday(singer.getBirthday())
                    .nationality(singer.getNationality())
                    .birthplace(singer.getBirthplace())
                    .build();
    }

    public static AlbumInfoDto buildAlbumInfoResp(Album album, Singer singer) {
        return AlbumInfoDto.builder()
                .id(album.getId())
                .singerId(album.getSingerId())
                .name(album.getName())
                .albumPicUrl(album.getAlbumPicUrl())
                .releaseTime(album.getReleaseTime())
                .description(album.getDescription())
                .singerName(singer.getName())
                .build();
    }

    public static Singer buildSinger(SingerReq singerReq) {
        Singer singer = new Singer();
        BeanUtils.copyProperties(singerReq, singer);
        return singer;
    }
}
