package io.github.frapples.osbrainsystem.biz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {

    private String content;
    private String answerContent;
    private String type;
}
