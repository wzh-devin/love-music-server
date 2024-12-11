package com.devin.love.music.service.v1;

import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.SingerReq;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    /**
     * 删除歌手信息
     * @param singerIds
     */
    void deleteSinger(@Valid List<Long> singerIds);

    /**
     * 上传歌手头像
     * @param uploadFile
     * @param id
     */
    void avatarUpload(MultipartFile uploadFile, Long id) throws IOException;

//    /**
//     * 上传歌手头像
//     * @param uploadFile
//     * @param id
//     */
//    void uploadSingerPic(MultipartFile uploadFile, Long id) throws IOException;
//
//    /**
//     * 文件下载
//     * @param fileName
//     */
//    void downloadFile(String fileName);
}
