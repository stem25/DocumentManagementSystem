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
        if(currentStatus == null || currentStatus.equals(OrderStatus.PREPARING)){
            statuses.add(OrderStatus.EXECUTING);
        }else if(currentStatus.equals(OrderStatus.EXECUTING)){
            statuses.add(OrderStatus.CONTROLING);
        }else if(currentStatus.equals(OrderStatus.CONTROLING)){
            statuses.add(OrderStatus.REVISION);
            statuses.add(OrderStatus.READY);
        }else if(currentStatus.equals(OrderStatus.REVISION)){
            statuses.add(OrderStatus.EXECUTING);
        }
        return statuses;
    }

}
