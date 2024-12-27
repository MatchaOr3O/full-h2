package com.example.backend.service;

import com.example.backend.model.Product;
import com.example.backend.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{

    //自動去連DAO資料庫
    @Autowired
    private ProductRepository productRepository;

    //自動覆蓋
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll(); //findAll把所有紀錄回傳回來
    }

    @Override
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    //存了多少Product就存多少product過來
    @Override
    public Product updateProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

}
