package io.github.frapples.osbrainsystem.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<UserDO> {

    List<UserDO> selectWithClass(Pagination page, @Param("ew") Wrapper<UserDO> wrapper);
}
