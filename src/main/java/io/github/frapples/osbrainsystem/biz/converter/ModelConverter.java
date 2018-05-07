package io.github.frapples.osbrainsystem.biz.converter;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

public class ModelConverter {

    public static <S, T> T convert(S src, Class<T> dstClass) {
        try {
            if (src instanceof Converter) {
                Converter converter = (Converter) src;
                T dst = (T) converter.convertTo(dstClass.newInstance());
                return dst;
            } else if (Converter.class.isAssignableFrom(dstClass)) {
                Converter converter = (Converter) dstClass.newInstance();
                T dst = (T) converter.convertFrom(src);
                return dst;
            } else {
                throw new ConvertException(
                    String.format("Class %s and %s at least one is Converter", src.getClass(), dstClass));
            }

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static <M, D> List<M> convert(List<D> srcList, Class<M> destClass) {
        ArrayList<M> results = Lists.newArrayList();

        for (D src : srcList) {
            results.add(convert(src, destClass));
        }
        return results;
    }
}
