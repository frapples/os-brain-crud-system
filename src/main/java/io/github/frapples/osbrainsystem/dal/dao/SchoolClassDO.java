package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("school_class")
public class SchoolClassDO extends BaseDO {
    private String name;
    private Integer startYear;
    @TableField(exist = false)
    private Integer userCount;
}
