package service;

import domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public List<Product> findAll() throws Exception;
    public void save(Product product) throws Exception;
}
