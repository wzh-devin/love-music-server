package com.devin.love.music.service.v1.builder;

import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.resp.AlbumInfoResp;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 2024/11/6 1:37
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public class SingerBuilder {

    public static SingerInfoResp buildSingerInfoResp(Singer singer, List<AlbumInfoResp> albumInfoResp) {
        return SingerInfoResp.builder()
                    .id(singer.getId())
                    .sex(singer.getSex())
                    .name(singer.getName())
                    .description(singer.getDescription())
                    .singerPicUrl(singer.getSingerPicUrl())
                    .birthday(singer.getBirthday())
                    .nationality(singer.getNationality())
                    .birthplace(singer.getBirthplace())
                    .albums(albumInfoResp)
                    .build();
    }


    public static AlbumInfoResp buildAlbumInfoResp(Album album) {
        return AlbumInfoResp.builder()
                .id(album.getId())
                .singerId(album.getSingerId())
                .name(album.getName())
                .albumPicUrl(album.getAlbumPicUrl())
                .releaseTime(album.getReleaseTime())
                .description(album.getDescription())
                .build();
    }
}
