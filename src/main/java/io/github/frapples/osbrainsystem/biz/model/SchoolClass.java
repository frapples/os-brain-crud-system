package io.github.frapples.osbrainsystem.biz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolClass {
    private Integer id;
    private String name;
    private Integer startYear;
}
