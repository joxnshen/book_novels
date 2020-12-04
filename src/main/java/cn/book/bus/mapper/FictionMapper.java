package cn.book.bus.mapper;

import cn.book.bus.domain.Fiction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 追风
 * @since 2019-12-16
 */
public interface FictionMapper extends BaseMapper<Fiction> {

    /**
     * 分页显示
     */
    IPage<Fiction> selectPageVo(Page page, @Param("state") Integer state);

}
