package io.github.frapples.osbrainsystem.web.dbConfig;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import java.time.Instant;
import java.util.Date;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", new Date(), metaObject);
        setFieldValByName("createdTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updatedTime", new Date(), metaObject);
    }
}
