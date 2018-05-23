package io.github.frapples.osbrainsystem.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseRecordDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseRecordMapper extends BaseMapper<ExerciseRecordDO> {

    List<ExerciseRecordDO> selectWithUser(@Param("ew") Wrapper<ExerciseRecordDO> wrapper);
}
