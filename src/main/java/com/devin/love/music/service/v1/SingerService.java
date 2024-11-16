package com.devin.love.music.service.v1;

import com.devin.love.music.domain.vo.resp.SingerInfoResp;

import java.util.List;

/**
 * 2024/11/6 0:53
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
public interface SingerService {

    /**
     * 获取歌手列表
     * @return
     */
    List<SingerInfoResp> getSingerList();
}
