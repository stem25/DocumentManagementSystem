package ru.it.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.it.Exception.NotFoundException;

import java.io.Serializable;
import java.util.List;

public enum OrderStatus {

    PREPARING(0L, "Подготовка"),
    EXECUTING(1L, "Исполнение"),
    CONTROLING(2L, "Контроль"),
    REVISION(3L, "Доработка"),
    READY(4L, "Прием");

    private Long id;

    private String title;

    OrderStatus(Long id, String title){
        this.id = id;
        this.title = title;
    }

    public static OrderStatus get(Long id){
        if(id == null) return null;
        for(OrderStatus orderStatus: values()){
            if(id.equals(orderStatus.id)){
                return orderStatus;
            }
        }
        throw new NotFoundException("Status doesnt exsist");
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
