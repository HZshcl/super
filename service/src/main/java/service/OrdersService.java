package service;

import domain.Orders;

import java.util.List;

public interface OrdersService {
    //未分页查询
//    List<Orders> findAll() throws Exception;
    //分页查询
    List<Orders> findAllByPage(int page,int size) throws Exception;

    Orders findById(String id) throws Exception;
}
