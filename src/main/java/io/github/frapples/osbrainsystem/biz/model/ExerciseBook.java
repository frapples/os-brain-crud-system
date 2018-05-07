package io.github.frapples.osbrainsystem.biz.model;

import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.ExerciseBookDO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseBook implements Converter<ExerciseBook, ExerciseBookDO> {
    private Integer id;
    private String name;
    private String comment;
    private Date createdTime;
    private Date updatedTime;
    private Integer count;
}
