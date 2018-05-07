package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.Task;
import io.github.frapples.osbrainsystem.dal.dao.TaskDO;
import io.github.frapples.osbrainsystem.dal.mapper.TaskMapper;
import io.github.frapples.osbrainsystem.dal.utils.CrudRepositoryUtils;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {

    @Autowired
    private TaskMapper taskMapper;


    public List<Task> getTasks() {
        List<TaskDO> dos = taskMapper.selectList(new EntityWrapper<>());
        List<Task> tasks = ModelConverter.convert(dos, Task.class);
        return tasks;
    }

    public List<Map<String, Object>> getTasksWithBook() {
        return taskMapper.selectWithBook(new EntityWrapper<>());
    }

    public boolean addTask(Task task) {
        return CrudRepositoryUtils.insert(taskMapper, task, TaskDO.class);
    }

    public boolean updateTask(Task task) {
        TaskDO taskDO = ModelConverter.convert(task, TaskDO.class);
        return taskMapper.updateById(taskDO) >= 0;
    }
}
