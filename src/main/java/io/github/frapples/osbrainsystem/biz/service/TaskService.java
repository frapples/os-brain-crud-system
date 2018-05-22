package io.github.frapples.osbrainsystem.biz.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.dto.response.apistatus.TaskReplyStatusEnum;
import io.github.frapples.osbrainsystem.biz.model.ExerciseRecord;
import io.github.frapples.osbrainsystem.biz.model.Task;
import io.github.frapples.osbrainsystem.dal.repository.TaskRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public ResponseDTO<List<Task>> getTasks() {
        return ResponseDTO.ofSuccess(
            taskRepository.getTasks());
    }

    public ResponseDTO<List<Map<String, Object>>> getTasksWithBook() {
        return ResponseDTO.ofSuccess(taskRepository.getTasksWithBook());
    }

    public ResponseDTO addTask(Task task) {
        return ResponseDTO.ofSuccess(taskRepository.addTask(task));
    }

    public ResponseDTO updateTask(Task task) {
        return ResponseDTO.ofSuccess(taskRepository.updateTask(task));
    }

    public ResponseDTO replyTask(ExerciseRecord exerciseRecord, Map<String, Object> answers) {
        int recordId = taskRepository.updateOrInsertRecord(exerciseRecord);
        Optional<Task> task = taskRepository.getTask(exerciseRecord.getTaskId());
        if (!task.isPresent()) {
            return ResponseDTO.ofError();
        }

        Date now = new Date();
        if (now.compareTo(task.get().getStartTime()) < 0 || now.compareTo(task.get().getEndTime()) > 0) {
            return ResponseDTO.ofFailed(TaskReplyStatusEnum.TIME_NOT_MATCH_STATUS);
        }

        for (String questionId : answers.keySet()) {
            String jsonAnswer;
            Object value = answers.get(questionId);
            if (value instanceof String) {
                jsonAnswer = (String) value;
            } else if (value instanceof List) {
                jsonAnswer = JSON.toJSONString(value);
            } else {
                return ResponseDTO.ofError();
            }

            taskRepository.updateOrInsertReply(recordId, Integer.parseInt(questionId), jsonAnswer);
        }

        return ResponseDTO.ofSuccess();
    }
}
