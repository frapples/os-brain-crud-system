package io.github.frapples.osbrainsystem.dal.utils;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.github.frapples.osbrainsystem.biz.converter.ModelConverter;
import java.util.List;
import java.util.Optional;

public class CrudRepositoryUtils {

    public static <D> boolean insert(BaseMapper<D> mapper, Object model, Class<D> clazz) {
        D DO = ModelConverter.convert(model, clazz);
        if (DO == null) {
            return false;
        } else {
            return mapper.insert(DO) >= 1;
        }
    }

    public static <M, D> Optional<M> getItemOneOrNull(BaseMapper<D> mapper, Class<M> classModel, Class<D> classDO, Wrapper<D> wrapper) {
        List<D> resultDO = mapper.selectList(wrapper);
        List<M> result = ModelConverter.convert(resultDO, classModel);
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(result.get(0));
        }
    }

    public static <M, D> Optional<M> getItemByUnique(BaseMapper<D> mapper, Class<M> classModel, Class<D> classDO,
        String col, Object value) {
        if (value == null) {
            return Optional.empty();
        } else {
            return getItemOneOrNull(mapper, classModel, classDO, new EntityWrapper<D>().eq(col, value));
        }
    }
}
