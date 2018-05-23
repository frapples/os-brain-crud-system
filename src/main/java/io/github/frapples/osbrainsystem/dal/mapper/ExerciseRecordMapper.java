package io.github.frapples.osbrainsystem.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseRecordDO;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface ExerciseRecordMapper extends BaseMapper<ExerciseRecordDO> {

    List<ExerciseRecordDO> selectWithUser(@Param("ew") Wrapper<ExerciseRecordDO> wrapper);
    List<Map<String, Object>> selectWithUserAndTaskAndQuestion(@Param("ew") Wrapper<ExerciseRecordDO> wrapper);
}
