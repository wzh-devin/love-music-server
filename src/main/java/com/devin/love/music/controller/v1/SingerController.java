package com.devin.love.music.controller.v1;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.annotation.Log;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.SingerReq;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;
import com.devin.love.music.service.v1.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 2024/11/6 0:38
 * <p>
 * 歌手控制器
 * </p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@ApiV1
@Slf4j
@Api(tags = "歌手相关接口")
@RestController
@RequestMapping("/singer")
@RequiredArgsConstructor
public class SingerController {

    private final SingerService singerService;

    @GetMapping("/list")
    @ApiOperation("获取歌手列表")
    @Log(desc = "获取歌手列表", module = "歌手模块")
    public ApiResult<List<SingerInfoResp>> getSingerList() {
        return ApiResult.success(singerService.getSingerList(), null);
    }

    @PostMapping("/add")
    @ApiOperation("新增歌手")
    @Log(desc = "新增歌手", module = "歌手模块")
    public ApiResult<Void> addSinger(@Valid @RequestBody SingerReq singerReq) {
        singerService.addSinger(singerReq);
        return ApiResult.success();
    }

    @PostMapping("/update")
    @ApiOperation("更新歌手信息")
    @Log(desc = "更新歌手信息", module = "歌手模块")
    public ApiResult<Void> updateSingerInfo(@Valid @RequestBody SingerReq singerReq) {
        singerService.updateSingerInfo(singerReq);
        return ApiResult.success();
    }
}
