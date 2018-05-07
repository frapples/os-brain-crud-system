package io.github.frapples.osbrainsystem.biz.converter;

import org.springframework.beans.BeanUtils;

public interface Converter<M, D> {

    default D convertTo(D DO) {
        BeanUtils.copyProperties(this, DO);
        return DO;
    }

    default M convertFrom(D DO) {
        BeanUtils.copyProperties(DO, this);
        return (M) this;
    }

    static void defaultConvert(Object src, Object target) {
        BeanUtils.copyProperties(src, target);
    }
}
