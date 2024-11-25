package com.devin.love.music.controller.v1;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPObject;
import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.annotation.Log;
import com.devin.love.music.common.constant.Version;
import com.devin.love.music.common.enums.HttpErrorEnum;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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

    private final HttpServletResponse response;

    @GetMapping("/list")
    @ApiOperation("获取歌手列表")
    @Log(desc = "获取歌手列表", module = "歌手模块", version = Version.V1)
    public ApiResult<List<SingerInfoResp>> getSingerList() {
        return ApiResult.success(singerService.getSingerList(), null);
    }

    @PostMapping("/add")
    @ApiOperation("新增歌手")
    @Log(desc = "新增歌手", module = "歌手模块", version = Version.V1)
    public ApiResult<Void> addSinger(@Valid @RequestBody SingerReq singerReq) {
        singerService.addSinger(singerReq);
        return ApiResult.success();
    }

    @PostMapping("/update")
    @ApiOperation("更新歌手信息")
    @Log(desc = "更新歌手信息", module = "歌手模块", version = Version.V1)
    public ApiResult<Void> updateSingerInfo(@Valid @RequestBody SingerReq singerReq) {
        singerService.updateSingerInfo(singerReq);
        return ApiResult.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除歌手信息")
    @Log(desc = "删除歌手信息", module = "歌手模块", version = Version.V1)
    public ApiResult<Void> deleteSinger(@Valid @RequestBody List<Long> singerIds) {
        singerService.deleteSinger(singerIds);
        return ApiResult.success();
    }

    @PostMapping("/upload")
    @ApiOperation("上传歌手头像")
    @Log(desc = "上传歌手头像", module = "歌手模块", version = Version.V1)
    public ApiResult<?> uploadSingerPic(@RequestParam("file") MultipartFile uploadFile,
                                           @RequestParam("id") Long id) throws IOException {
        singerService.uploadSingerPic(uploadFile, id);
        return ApiResult.success();
    }

    @GetMapping("/download/{fileName}")
    @ApiOperation("文件下载")
    @Log(desc = "歌手头像下载", module = "歌手模块", version = Version.V1)
    public void downloadFile(@PathVariable("fileName") String fileName) throws IOException {
        try {
            singerService.downloadFile(fileName);
        } catch (Exception e) {
            // 手动向前端打入信息，防止将ApiResult对象转换为二进制，产生没有转换器的报错
            log.error("File Download Error: {}", e.getMessage());
            HttpErrorEnum.SYS_ERROR.sendHttpError(response);
        }
    }
}
