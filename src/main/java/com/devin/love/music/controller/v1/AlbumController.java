package com.devin.love.music.controller.v1;

import com.devin.love.music.common.annotation.ApiV1;
import com.devin.love.music.common.annotation.Log;
import com.devin.love.music.common.constant.Version;
import com.devin.love.music.domain.dto.AlbumInfoDto;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.vo.req.AlbumReq;
import com.devin.love.music.domain.vo.resp.ApiResult;
import com.devin.love.music.service.v1.AlbumService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 2024/11/25 8:47
 * <p>
 *     专辑控制器
 * </p>
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@ApiV1
@Slf4j
@Api(tags = "专辑相关接口")
@RequestMapping("/album")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/getAlbums")
    @ApiOperation("获取所有的专辑信息")
    @Log(desc = "获取所有的专辑", module = "专辑模块", version = Version.V1)
    public ApiResult<List<AlbumInfoDto>> getAllAlbumList() {
        return ApiResult.success(albumService.getAllAlbumList(), null);
    }

    @GetMapping("/getSingerAlbums")
    @ApiOperation("获取歌手专辑列表")
    @Log(desc = "获取歌手专辑列表", module = "专辑模块", version = Version.V1)
    public ApiResult<List<AlbumInfoDto>> getSingerAlbums(Long singerId) {
        return ApiResult.success(albumService.getSingerAlbums(singerId), null);
    }

    @PostMapping("/addAlbum")
    @ApiOperation("新增歌手专辑")
    @Log(desc = "新增歌手专辑", module = "专辑模块", version = Version.V1)
    public ApiResult<Void> addAlbum(@RequestBody AlbumInfoDto albumInfoDto) {
        albumService.addAlbum(albumInfoDto);
        return ApiResult.success();
    }

    @PostMapping("/editAlbum")
    @ApiOperation("修改歌手专辑")
    @Log(desc = "修改歌手专辑", module = "专辑模块", version = Version.V1)
    public ApiResult<Void> editAlbum(@RequestBody AlbumInfoDto albumInfoDto) {
        albumService.editAlbum(albumInfoDto);
        return ApiResult.success();
    }

    @PostMapping("/delAlbum")
    @ApiOperation("删除歌手专辑")
    @Log(desc = "删除歌手专辑", module = "专辑模块", version = Version.V1)
    public ApiResult<Void> delAlbum(@RequestBody AlbumReq albumReq) {
        albumService.delAlbum(albumReq);
        return ApiResult.success();
    }
}
