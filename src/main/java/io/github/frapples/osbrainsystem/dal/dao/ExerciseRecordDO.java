package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("exercise_record")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ExerciseRecordDO extends BaseDO {
    private Date startTime;
    private Date endTime;
    private Integer taskId;
    private Integer userId;
    @TableField(exist = false)
    private String studentId;

    private Float score;
    private String ip;
    private String gps;
}
