package io.github.frapples.osbrainsystem.dal.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;

public interface UserMapper extends BaseMapper<UserDO> {
    default int test() {
        return 0;
    }
}
