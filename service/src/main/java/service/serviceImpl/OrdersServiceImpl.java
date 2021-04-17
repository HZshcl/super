package service.serviceImpl;

import com.github.pagehelper.PageHelper;
import dao.OrdersDao;
import domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.OrdersService;

import java.util.List;
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;
    //未分页查询
//    @Override
//    public List<Orders> findAll() throws Exception {
//        return ordersDao.findAll();
//    }
//
    //分页查询
    @Override
    public List<Orders> findAllByPage(int page, int size) throws Exception {
        //一定要紧贴真正执行查询之前分页
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(String id) throws Exception {
        return ordersDao.findById(id);
    }


}
