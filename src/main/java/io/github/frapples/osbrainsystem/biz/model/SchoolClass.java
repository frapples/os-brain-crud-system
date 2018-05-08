package io.github.frapples.osbrainsystem.biz.model;

import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.SchoolClassDO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolClass implements Converter<SchoolClass, SchoolClassDO> {

    private Integer id;
    private String name;
    private Integer startYear;
    private Date createdTime;
    private Integer userCount;
}
