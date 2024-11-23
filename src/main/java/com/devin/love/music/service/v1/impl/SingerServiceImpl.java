package com.devin.love.music.service.v1.impl;

import cn.hutool.core.util.ObjectUtil;
import com.devin.love.music.common.enums.ExecuteStatusEnum;
import com.devin.love.music.common.exception.FileBusinessException;
import com.devin.love.music.common.utils.AssertUtil;
import com.devin.love.music.common.utils.SnowFlake;
import com.devin.love.music.dao.v1.AlbumDao;
import com.devin.love.music.dao.v1.MusicDao;
import com.devin.love.music.dao.v1.SingerDao;
import com.devin.love.music.domain.entity.Album;
import com.devin.love.music.domain.entity.Music;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.domain.vo.req.SingerReq;
import com.devin.love.music.domain.vo.resp.AlbumInfoResp;
import com.devin.love.music.domain.vo.resp.SingerInfoResp;
import com.devin.love.music.service.v1.SingerService;
import com.devin.love.music.service.v1.builder.SingerBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 2024/11/6 0:53
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SingerServiceImpl implements SingerService {

    /**
     * 上传头像的文件夹路径
     */
    public static final String UPLOAD_SINGER_AVATARS = "/upload/singer/avatars/";

    private final HttpServletResponse response;
    private final SingerDao singerDao;
    private final AlbumDao albumDao;
    private final MusicDao musicDao;

    @Override
    public List<SingerInfoResp> getSingerList() {
        // 查询所有歌手
        List<Singer> singers = singerDao.getAllSingers();
        if (singers.isEmpty()) {
            // 如果歌手为空，直接返回
            return null;
        }

        // 封装歌手信息
        return singers.stream().map(singer -> {
            // 查询歌手的专辑信息
            List<Album> albumList = albumDao.getAlbumsBySingerIds(List.of(singer.getId()));
            // 封装专辑信息
            List<AlbumInfoResp> albumInfoRespList = albumList.stream().map(album -> {
                AlbumInfoResp albumInfoResp = SingerBuilder.buildAlbumInfoResp(album);
                // 查询专辑中的歌曲
                albumInfoResp.setMusicTotal(musicDao.getMusicCount(album.getSingerId(), album.getId()));
                return albumInfoResp;
            }).toList();
            return SingerBuilder.buildSingerInfoResp(singer, albumInfoRespList);
        }).toList();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addSinger(SingerReq singerReq) {
        addSingerCommon(singerReq, ExecuteStatusEnum.ADD);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateSingerInfo(SingerReq singerReq) {
        if (Objects.isNull(singerReq)) return;
        // 删除歌手信息
        delSingerCommon(List.of(singerReq.getId()));
        // 新增歌手
        addSingerCommon(singerReq, ExecuteStatusEnum.UPDATE);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteSinger(List<Long> singerIds) {
        if (singerIds.isEmpty()) return;
        // 删除歌手信息
        delSingerCommon(singerIds);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void uploadSingerPic(MultipartFile uploadFile, Long id) throws IOException {
        // TODO 后续接入Minio技术，将图片上传到Minio
        Singer singer = singerDao.getById(id);
        try (InputStream is = uploadFile.getInputStream()) {
            // 获取MD5值
            String md5 = DigestUtils.md5DigestAsHex(is);
            if (checkFileDuplicate(singer, md5)) return;
            // 获取原始文件名
            String[] originName = Objects.requireNonNull(uploadFile.getOriginalFilename()).split("\\.");
            String prefix = originName[0];
            String suffix = originName[originName.length - 1];
            // 设置上传的文件名
            String fileName = prefix + "_" + SnowFlake.nextId() + "." + suffix;
            // 上传路径
            File dirPath = new File(System.getProperty("user.dir") + UPLOAD_SINGER_AVATARS);
            // 判断上传路径是否存在
            if (!dirPath.exists()) dirPath.mkdirs();
            // 将文件上传到此目录下
            uploadFile.transferTo(new File(dirPath, fileName));
            // 将文件信息存入到数据库
            singer.setSingerPicUrl(fileName);
            singer.setFileMd5(md5);
            singerDao.updateById(singer);
        } catch (Exception e) {
            throw new FileBusinessException("文件上传异常" + e.getMessage());
        }
    }

    /**
     * 文件重复性检查
     *
     * @param singer
     * @param fileMd5
     */
    private boolean checkFileDuplicate(Singer singer, String fileMd5) {
        // 判断md5值
        return !ObjectUtils.isEmpty(singer.getFileMd5()) && singer.getFileMd5().equals(fileMd5);
    }

    @Override
    public void downloadFile(String fileName) {
        // TODO 后续接入Minio技术，从Minio下载文件
        File file = new File(System.getProperty("user.dir") + UPLOAD_SINGER_AVATARS + fileName);
        if (!file.exists()) {
            throw new FileBusinessException("文件不存在");
        }

        // 设置响应体
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));

        // 下载文件
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file)); ServletOutputStream os = response.getOutputStream()) {
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = bis.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            os.flush();
            return; // 确保在写入文件内容后立即返回
        } catch (Exception e) {
            // 失败
            log.error("文件下载失败：{}", e.getMessage());
            throw new FileBusinessException(e.getMessage());
        }
    }

    /**
     * 新增歌手逻辑抽取
     *
     * @param singerReq
     * @param executeStatus 执行状态
     */
    private void addSingerCommon(SingerReq singerReq, ExecuteStatusEnum executeStatus) {
        if (Objects.isNull(singerReq)) return;

        // 构建歌手实体
        Singer singer = SingerBuilder.buildSinger(singerReq);

        // 如果是新增，则生成新的id
        if (ExecuteStatusEnum.ADD.name().equals(executeStatus.name())) {
            singer.setId(SnowFlake.nextId());
        }

        log.info("singer: {}", singer);
        boolean saveResult = singerDao.save(singer);
        AssertUtil.isTrue(saveResult, "歌手插入失败");

        // 判断专辑是否存在
        if (!singerReq.getAlbums().isEmpty()) {
            // 构建专辑列表，产生关联
            List<Album> albums = singerReq.getAlbums().stream().peek(album -> album.setSingerId(singer.getId())).toList();
            // 插入专辑
            boolean albumResult = albumDao.saveBatch(albums);
            AssertUtil.isTrue(albumResult, "专辑插入失败");
        }
    }

    /**
     * 公共删除逻辑
     *
     * @param singerIds TODO 后续会支持批量删除，所以这里需要传入歌手id列表，以达到复用
     */
    private void delSingerCommon(List<Long> singerIds) {
        // 删除音乐信息
        if (!musicDao.getMusicBySingerIds(singerIds).isEmpty()) {
            boolean delMusicResult = musicDao.deleteBySingerIds(singerIds);
            AssertUtil.isTrue(delMusicResult, "歌曲删除失败");
        }
        // 删除专辑信息
        if (!albumDao.getAlbumsBySingerIds(singerIds).isEmpty()) {
            boolean delAlbumResult = albumDao.deleteBySingerIds(singerIds);
            AssertUtil.isTrue(delAlbumResult, "专辑删除失败");
        }
        // 删除歌手信息
        boolean delSingerResult = singerDao.removeByIds(singerIds);
        AssertUtil.isTrue(delSingerResult, "歌手删除失败");
    }
}
