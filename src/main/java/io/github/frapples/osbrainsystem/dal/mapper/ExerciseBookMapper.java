package io.github.frapples.osbrainsystem.dal.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.github.frapples.osbrainsystem.biz.model.Question;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseBookDO;
import io.github.frapples.osbrainsystem.dal.dao.QuestionDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExerciseBookMapper extends BaseMapper<ExerciseBookDO> {

    List<QuestionDO> selectQuestions(@Param("ew") Wrapper<ExerciseBookDO> wrapper);

}
