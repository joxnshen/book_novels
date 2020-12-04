package cn.book.bus.service.impl;

import cn.book.bus.domain.ChapterContent;
import cn.book.bus.mapper.ChapterContentMapper;
import cn.book.bus.service.IChapterContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 追风
 * @since 2019-12-21
 */
@Service
public class ChapterContentServiceImpl extends ServiceImpl<ChapterContentMapper, ChapterContent> implements IChapterContentService {

}
