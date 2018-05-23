package io.github.frapples.osbrainsystem.biz.model;

import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseRecordDO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExerciseRecord implements Converter<ExerciseRecord, ExerciseRecordDO> {
    private Integer id;
    private Date startTime;
    private Date endTime;
    private Integer taskId;
    private Integer userId;
    private String studentId;
    private Float score;
    private String ip;
    private String gps;
}
