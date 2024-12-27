package com.example.backend.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Product;
import com.example.backend.service.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping //對應取得所有函數
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    
    //呼叫指定函數
    @GetMapping("/{id}") //設id當變數去抓變數
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(
          ()-> ResponseEntity.notFound().build()
        );
    }

    @PostMapping //新增一筆紀錄
    public Product createProduct(@RequestBody Product product){  //創建 / 抓Requestbody
        return productService.saveProduct(product);
    }


    @PutMapping("{/id}") //更新某一筆紀錄
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetail){ //ResponseEntity回傳到Product， @ 對應裡面的資料 對應表單裡面的id資料
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()){ //有找到，更新資料，對應表單
            Product productNew = product.get();
            productNew.setName(productDetail.getName());  //設定為使用者輸入表單的名稱
            productNew.setDescription(productDetail.getDescription()); //設定為使用者輸入表單的描述
            productNew.setPrice(productDetail.getPrice()); //設定為使用者輸入表單的價格
            return ResponseEntity.ok(productService.updateProduct(productNew));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/{id}") //設id當變數去抓變數
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        Optional<Product> product = productService.getProductById(id);
        if(product.isPresent()){ //呼叫productService
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
