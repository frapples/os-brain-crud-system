package io.github.frapples.osbrainsystem.biz.model;

import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.TaskDO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task implements Converter<Task, TaskDO> {
    private Integer id;
    private Integer exerciseBookId;
    private Date startTime;
    private Date endTime;
}
