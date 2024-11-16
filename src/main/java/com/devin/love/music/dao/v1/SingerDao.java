package com.devin.love.music.dao.v1;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devin.love.music.domain.entity.Singer;
import com.devin.love.music.mapper.v1.SingerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2024/11/6 0:52
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Service
public class SingerDao extends ServiceImpl<SingerMapper, Singer> {

    /**
     * 获取所有歌手列表
     *
     * @return
     */
    public List<Singer> getAllSingers() {
        return lambdaQuery().list();
    }
}
