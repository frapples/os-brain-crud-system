package io.github.frapples.osbrainsystem.biz.converter;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class DefaultConverter {

    /**
     * Default converter
     * @param src
     * @param dest
     */
    public static <T> T convert(Object src, Class<T> type) {
        try {
            T dest = type.newInstance();
            BeanUtils.copyProperties(src, dest);
            return dest;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> convert(List list, Class<T> type) {
        List<T> result = Lists.newArrayList();
        for (Object o : list) {
            result.add(convert(o, type));
        }
        return result;
    }
}
