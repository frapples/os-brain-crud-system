package io.github.frapples.osbrainsystem.biz.service;

import io.github.frapples.osbrainsystem.biz.dto.response.ResponseDTO;
import io.github.frapples.osbrainsystem.biz.model.Task;
import io.github.frapples.osbrainsystem.dal.repository.TaskRepository;
import java.util.List;
import java.util.Map;
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
}
