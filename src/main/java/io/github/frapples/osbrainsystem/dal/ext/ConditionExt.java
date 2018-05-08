package io.github.frapples.osbrainsystem.dal.ext;

import com.baomidou.mybatisplus.mapper.Condition;
import com.google.common.base.Strings;

public class ConditionExt extends Condition {

    public static ConditionExt create() {
        return new ConditionExt();
    }

    public ConditionExt eqWhenNotEmpty(String col, String value) {
        eq(!Strings.isNullOrEmpty(value), col, value);
        return this;
    }

    public ConditionExt eqWhenNotNull(String col, String value) {
        eq(value != null, col, value);
        return this;
    }

}
