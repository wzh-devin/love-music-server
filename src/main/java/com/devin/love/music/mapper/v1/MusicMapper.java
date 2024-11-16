package com.devin.love.music.mapper.v1;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.devin.love.music.domain.entity.Music;
import org.apache.ibatis.annotations.Mapper;

/**
 * 2024/11/7 1:55
 * <p></p>
 *
 * @author <a href="https://github.com/wzh-devin">devin</a>
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface MusicMapper extends BaseMapper<Music> {
}
