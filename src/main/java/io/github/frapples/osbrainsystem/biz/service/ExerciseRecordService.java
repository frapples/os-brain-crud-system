package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.ExerciseRecord;
import io.github.frapples.osbrainsystem.dal.repository.ExerciseBookRepository;
import io.github.frapples.osbrainsystem.dal.repository.ExerciseRecordRepository;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseRecordService {

    @Autowired
    private ExerciseRecordRepository exerciseRecordRepository;

    public ResponseDTO<List<Map<String, Object>>> getRecordDetailsByTaskId(Integer taskId) {
        return ResponseDTO.ofSuccess(
            exerciseRecordRepository.getRecordDetailsByTaskId(taskId));
    }

    public ResponseDTO<List<Map<String,Object>>> getUserExerciseRecordDetails(String studentId) {
        return ResponseDTO.ofSuccess(
            exerciseRecordRepository.getRecordDetailsByStudentId(studentId));
    }
}
