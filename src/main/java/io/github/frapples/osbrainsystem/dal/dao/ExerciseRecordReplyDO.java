package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("exercise_record_reply")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ExerciseRecordReplyDO extends BaseDO {
    private Integer exerciseRecordId;
    private Integer questionId;
    private String answerContent;
}
