package io.github.frapples.osbrainsystem.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.github.frapples.osbrainsystem.dal.dao.TaskDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper extends BaseMapper<TaskDO> {

    List<Map<String, Object>> selectWithBook(@Param("ew") Wrapper<TaskDO> wrapper);
}
