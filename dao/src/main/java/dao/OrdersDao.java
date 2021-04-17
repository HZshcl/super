package dao;

import domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrdersDao {
    @Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column="orderNum",property="orderNum"),
            @Result(column="orderTime",property="orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property="peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property ="orderDesc" ),
            @Result(column = "productId",property = "product",one=@One(select = "dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id=#{id}")
    @Results(
            {
                    @Result(id=true,column ="id",property = "id"),
                    @Result(column="orderNum",property="orderNum"),
                    @Result(column="orderTime",property="orderTime"),
                    @Result(column = "orderStatus",property = "orderStatus"),
                    @Result(column = "peopleCount",property="peopleCount"),
                    @Result(column = "payType",property = "payType"),
                    @Result(column = "orderDesc",property ="orderDesc" ),
                    @Result(column = "productId",property = "product",one=@One(select = "dao.ProductDao.findById")),
                    @Result(column = "memberId",property = "member",one=@One(select = "dao.MemberDao.findById")),
                    @Result(column = "id",property = "travellers",many=@Many(select = "dao.TravellerDao.findByOrdersId"))
            }
    )
    Orders findById(String id) throws Exception;


}
