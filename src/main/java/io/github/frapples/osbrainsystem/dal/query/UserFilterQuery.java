package io.github.frapples.osbrainsystem.dal.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterQuery {
    private String name;
    private String studentId;
    private String classId;
}
