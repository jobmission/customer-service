package com.revengemission.customerservice.service;

import com.revengemission.customerservice.domain.JsonObjects;
import com.revengemission.customerservice.domain.NotImplementException;
import com.revengemission.customerservice.domain.ResponseResult;

import java.util.List;

public interface CommonInterface<T> {
    default JsonObjects<T> list(int pageNum,
                                int pageSize,
                                String sortField,
                                String sortOrder) {
        throw new NotImplementException();
    }

    default T create(T t) {
        throw new NotImplementException();
    }

    default T retrieveById(long id) {
        throw new NotImplementException();
    }

    default T retrieveUniqueByField(String field, String value) throws NotImplementException {
        throw new NotImplementException();
    }

    default List<T> retrieveListByField(String field, String value) {
        throw new NotImplementException();
    }

    default ResponseResult updateById(T t) {
        throw new NotImplementException();
    }

    default ResponseResult deleteById(long id) {
        throw new NotImplementException();
    }
}
