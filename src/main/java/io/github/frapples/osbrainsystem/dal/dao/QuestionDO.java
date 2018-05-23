package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("question")
@Builder
public class QuestionDO extends BaseDO {

    private String content;
    private String answerContent;
    private String type;
    private String choiseOption;
    private Float score;
}
