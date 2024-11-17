package com.devin.love.music.service.v1;

import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.SingerReq;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;

import javax.validation.Valid;
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

    /**
     * 新增歌手
     * @param singerReq
     * @return
     */
    void addSinger(SingerReq singerReq);

    /**
     * 更新歌手信息
     * @param singerReq
     */
    void updateSingerInfo(@Valid SingerReq singerReq);
}
