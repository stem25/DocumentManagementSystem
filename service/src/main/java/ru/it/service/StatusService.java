package ru.it.service;

import ru.it.model.OrderStatus;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class StatusService {

    public List<OrderStatus> getAvailableStatuses(OrderStatus currentStatus){
        List<OrderStatus> statuses = new ArrayList<>();
        if(currentStatus == null || currentStatus.getId().equals(OrderStatus.PREPARING.getId())){
            statuses.add(OrderStatus.EXECUTING);
        }else if(currentStatus.getId().equals(OrderStatus.EXECUTING.getId())){
            statuses.add(OrderStatus.CONTROLING);
        }else if(currentStatus.getId().equals(OrderStatus.CONTROLING.getId())){
            statuses.add(OrderStatus.REVISION);
            statuses.add(OrderStatus.READY);
        }else if(currentStatus.getId().equals(OrderStatus.REVISION.getId())){
            statuses.add(OrderStatus.EXECUTING);
        }
        return statuses;
    }

}
