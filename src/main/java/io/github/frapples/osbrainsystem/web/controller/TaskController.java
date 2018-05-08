package io.github.frapples.osbrainsystem.web.controller;

import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.Task;
import io.github.frapples.osbrainsystem.biz.service.TaskService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseDTO addTask(@RequestParam String exerciseBookId,
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
        @RequestParam String exerciseBookId,
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
}
