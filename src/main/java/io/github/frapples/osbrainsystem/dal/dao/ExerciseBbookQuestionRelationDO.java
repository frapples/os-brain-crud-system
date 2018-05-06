package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("exercise_book_question_relation")
public class ExerciseBbookQuestionRelationDO extends BaseDO {
    Integer questionId;
    Integer exerciseBookId;
    Integer orderKey;
}
