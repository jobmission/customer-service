package com.revengemission.customerservice.service.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseServiceImpl {
    @Autowired
    Mapper dozerMapper;

    public <T, U> List<T> mapperListObjects(List<U> source, Class<T> t) {
        List<T> result = null;
        if (source != null && source.size() > 0) {
            result = new ArrayList<>();
            for (U o : source) {
                if (o != null) {
                    result.add(dozerMapper.map(o, t));
                }
            }
        }
        return result;
    }
}
