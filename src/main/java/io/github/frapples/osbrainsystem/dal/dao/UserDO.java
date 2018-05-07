package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("user")
public class UserDO extends BaseDO {
    private String name;
    private String studentId;
    private String classId;
    private String email;
    private String phone;
    @TableField(exist = false)
    private String className;
    @TableField(exist = false)
    private Integer classStartYear;
}
