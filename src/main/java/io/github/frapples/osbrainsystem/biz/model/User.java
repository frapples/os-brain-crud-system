package io.github.frapples.osbrainsystem.biz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import io.github.frapples.osbrainsystem.biz.converter.Converter;
import io.github.frapples.osbrainsystem.dal.dao.UserDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Converter<User, UserDO> {

    private String name;
    private String studentId;
    private String classId;
    private String email;
    private String phone;
    private String className;
    private Integer classStartYear;
}
