package io.github.frapples.osbrainsystem.dal.repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import io.github.frapples.osbrainsystem.biz.model.ExerciseBook;
import io.github.frapples.osbrainsystem.biz.model.Task;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseBookDO;
import io.github.frapples.osbrainsystem.dal.dao.TaskDO;
import io.github.frapples.osbrainsystem.dal.mapper.ExerciseBookMapper;
import io.github.frapples.osbrainsystem.dal.mapper.TaskMapper;
import io.github.frapples.osbrainsystem.dal.utils.CrudRepositoryUtils;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private ExerciseBookMapper exerciseBookMapper;


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

    public List<ExerciseBook> getbooksByUsersPhone(String phone) {
        Date now = new Date();
        List<TaskDO> result = taskMapper.selectList(
            new EntityWrapper<TaskDO>()
                .le("start_time", now)
                .ge("end_time", now));
        List<Integer> bookIds = result.stream().map(TaskDO::getExerciseBookId).collect(Collectors.toList());

        if (bookIds.isEmpty()) {
            return Lists.newArrayList();
        } else {
            List<ExerciseBookDO> books = exerciseBookMapper.selectList(new EntityWrapper<ExerciseBookDO>()
                .in("id", bookIds));
            return ModelConverter.convert(books, ExerciseBook.class);
        }
    }
}
