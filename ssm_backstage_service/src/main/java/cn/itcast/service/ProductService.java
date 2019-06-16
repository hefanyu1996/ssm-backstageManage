package cn.itcast.service;


import cn.itcast.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll(Integer page,Integer size) throws Exception;

    void save(Product product) throws Exception;


}
