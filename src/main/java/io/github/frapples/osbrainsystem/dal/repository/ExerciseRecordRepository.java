package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.ExerciseRecord;
import io.github.frapples.osbrainsystem.biz.model.User;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseRecordDO;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseRecordReplyDO;
import io.github.frapples.osbrainsystem.dal.exception.DataConsistencyException;
import io.github.frapples.osbrainsystem.dal.exception.InvalidDataException;
import io.github.frapples.osbrainsystem.dal.mapper.ExerciseRecordMapper;
import io.github.frapples.osbrainsystem.dal.mapper.ExerciseRecordReplyMapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExerciseRecordRepository {

    @Autowired
    private ExerciseRecordMapper exerciseRecordMapper;

    @Autowired
    private ExerciseRecordReplyMapper exerciseRecordReplyMapper;

    @Autowired
    private UserRepository userRepository;

    public int updateOrInsertRecord(ExerciseRecord exerciseRecord) {
        Optional<User> user = userRepository.getUser(exerciseRecord.getStudentId());
        Integer id = user.orElseThrow(InvalidDataException::new).getId();
        if (id == null) {
            throw new DataConsistencyException();
        }
        exerciseRecord.setUserId(id);

        Optional<ExerciseRecordDO> oldExerciseRecordDO = this.getRecordByTaskIdAndStudentId(
            exerciseRecord.getTaskId(), exerciseRecord.getStudentId());
        ExerciseRecordDO exerciseRecordDO = ModelConverter.convert(exerciseRecord, ExerciseRecordDO.class);
        if (oldExerciseRecordDO.isPresent()) {
            exerciseRecordDO.setId(oldExerciseRecordDO.get().getId());
            exerciseRecordMapper.updateById(exerciseRecordDO);
        } else {
            exerciseRecordMapper.insert(exerciseRecordDO);
        }
        return exerciseRecordDO.getId();
    }

    public boolean updateOrInsertReply(Integer recordId, Integer questionId, String answer) {
        if (recordId == null || questionId == null) {
            return false;
        }

        List<ExerciseRecordReplyDO> result = exerciseRecordReplyMapper.selectList(
            new EntityWrapper<ExerciseRecordReplyDO>()
                .eq("exercise_record_id", recordId)
                .eq("question_id", questionId));
        if (result.isEmpty()) {
            ExerciseRecordReplyDO exerciseRecordReplyDO = ExerciseRecordReplyDO.builder()
                .exerciseRecordId(recordId)
                .questionId(questionId)
                .answerContent(answer)
                .build();
            return exerciseRecordReplyMapper.insert(exerciseRecordReplyDO) > 0;
        } else {
            ExerciseRecordReplyDO exerciseRecordReplyDO = result.get(0);
            exerciseRecordReplyDO.setAnswerContent(answer);
            return exerciseRecordReplyMapper.updateById(exerciseRecordReplyDO) > 0;
        }
    }

    private Optional<ExerciseRecordDO> getRecordByTaskIdAndStudentId(Integer taskId, String studentId) {
        if (taskId == null || studentId == null) {
            return Optional.empty();
        } else {
            List<ExerciseRecordDO> result = exerciseRecordMapper.selectWithUser(new EntityWrapper<ExerciseRecordDO>()
                .eq("task_id", taskId)
                .eq("student_id", studentId));
            return result.isEmpty() ?
                Optional.empty() :
                Optional.ofNullable(result.get(0));
        }
    }

    public List<Map<String, Object>> getRecordDetailsByTaskId(Integer taskId) {
        if (taskId == null) {
            return Lists.newArrayList();
        }
        List<Map<String, Object>> result = exerciseRecordMapper.selectWithUserAndTaskAndQuestion(
            new EntityWrapper<ExerciseRecordDO>()
            .eq("task_id", taskId));
        return result;
    }

    public List<Map<String, Object>> getRecordDetailsByStudentId(String studentId) {
        if (studentId == null) {
            return Lists.newArrayList();
        }
        List<Map<String, Object>> result = exerciseRecordMapper.selectWithUserAndTaskAndQuestion(
            new EntityWrapper<ExerciseRecordDO>()
                .eq("user.student_id", studentId));
        return result;
    }
}
