package dao;

import domain.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional //事务提交
public interface ProductDao {
    //查询所以产品信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;
    //新增信息
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus}) ")
    public void save(Product product) throws Exception;
    //删除信息
    @Delete("delete from product where id=#{id}")
    public void delete(String id) throws Exception;

    //根据id查询信息
    @Select("select * from product where id=#{id}")
    Product findById(String id) throws Exception;

}
