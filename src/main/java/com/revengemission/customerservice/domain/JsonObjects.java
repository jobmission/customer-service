package com.revengemission.customerservice.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonObjects<T> implements Serializable {
    private List<T> objectElements;
    private Integer currentPage;
    private Integer totalPage;
    private Long total;


    public void setObjectElements(List<T> objectElements) {
        this.objectElements = objectElements;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getObjectElements() {
        if (objectElements == null) {
            objectElements = new LinkedList<T>();
        }
        return objectElements;
    }
}
