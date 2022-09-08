package com.vuhien.application;



import com.vuhien.application.repository.ProductSizeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductSizeTest {

    @Autowired
    private ProductSizeRepository productSizeRepository;

    @Test
    void findAllSizeOfProduct_Test(){
        List<Integer> sizes = productSizeRepository.findAllSizeOfProduct("Eq1H5L");
        System.out.println(sizes);
    }

}
