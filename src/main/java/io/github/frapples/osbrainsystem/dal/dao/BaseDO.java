package io.github.frapples.osbrainsystem.dal.dao;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

public class BaseDO {

    @TableId(type = IdType.AUTO)
    private Integer id;
}