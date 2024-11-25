package com.devin.love.music.controller.v1;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.annotation.Log;
import com.devin.love.music.common.constant.Version;
import com.devin.love.music.domain.dto.MusicDto;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.devin.love.music.service.v1.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 2024/11/7 1:57
 * <p>
 *     音乐控制器
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@ApiV1
@RestController
@RequestMapping("/music")
@Api(tags = "音乐相关接口")
@RequiredArgsConstructor
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/getMusicList")
    @ApiOperation("查询相关音乐")
    @Log(desc = "查询音乐列表", module = "音乐模块", version = Version.V1)
    public ApiResult<List<MusicDto>> getMusicList(@RequestParam("singerId") Long singerId, @RequestParam("albumId") Long albumId) {
        return ApiResult.success(musicService.getMusicList(singerId, albumId), null);
    }
}
