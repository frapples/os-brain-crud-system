package io.github.frapples.osbrainsystem.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.ExerciseRecord;
import io.github.frapples.osbrainsystem.biz.model.Task;
import io.github.frapples.osbrainsystem.biz.service.TaskService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;


    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO addTask(@RequestParam Integer exerciseBookId,
        @RequestParam Long startTime,
        @RequestParam Long endTime) {
        Task task = Task.builder()
            .exerciseBookId(exerciseBookId)
            .startTime(new Date(startTime))
            .endTime(new Date(endTime))
            .build();
        log.info("add task with: {}", task);
        return taskService.addTask(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseDTO editTask(@PathVariable Integer id,
        @RequestParam Integer exerciseBookId,
        @RequestParam Long startTime,
        @RequestParam Long endTime) {
        Task task = Task.builder()
            .id(id)
            .exerciseBookId(exerciseBookId)
            .startTime(new Date(startTime))
            .endTime(new Date(endTime))
            .build();
        log.info("add task with: {}", task);
        return taskService.updateTask(task);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO<List<Map<String, Object>>> getTasks() {
        log.info("get all tasks");
        return taskService.getTasksWithBook();
    }

    @RequestMapping(value = "/{taskId}/reply", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO replyTask(@PathVariable Integer taskId, @RequestParam Integer studentId,
        @RequestParam Long startTime,
        @RequestParam("answers") String jsonAnswers) {
        log.info("Reply task");
        Map answers = JSON.parseObject(jsonAnswers, new TypeReference<Map<String, Object>>(){});
        ExerciseRecord exerciseRecord = ExerciseRecord.builder()
            .taskId(taskId)
            .studentId(studentId)
            .startTime(new Date(startTime))
            .endTime(new Date())
            .build();
        return taskService.replyTask(exerciseRecord, answers);
    }
}
