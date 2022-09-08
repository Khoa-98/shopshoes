package com.vuhien.application;



import com.vuhien.application.entity.Product;
import com.vuhien.application.model.dto.DetailProductInfoDTO;
import com.vuhien.application.model.dto.ProductInfoDTO;
import com.vuhien.application.repository.ProductRepository;
import com.vuhien.application.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;


    @Test
    void getListNewProductsTest(){
        List<ProductInfoDTO> productInfoDTOS = productRepository.getListNewProducts(5);
        productInfoDTOS.forEach(System.out::println);

    }

//    @Test
//    void getRelatedProducts_Test(){
//        Optional<Product> product = productRepository.findById("4iKReQ");
//        List<ProductInfoDTO> productInfoDTOS = productRepository.getRelatedProducts("4iKReQ", 3);
//        System.out.println(productInfoDTOS);
//        for (ProductInfoDTO p: productInfoDTOS) {
//            assertThat(p.getId().equals(product.get().getId())).isFalse();
//        }
//
//    }

    @Test
    void getDetailProductById_Test(){

        DetailProductInfoDTO detailProductInfoDTOS_2 = productService.getDetailProductById("4iKReQ");
        System.out.println(detailProductInfoDTOS_2);

    }

    @Test
    void searchProductAllSize_Test(){
        List<Long> brands = new ArrayList<>();
        brands.add(1L);
        brands.add(2L);
        brands.add(3L);
        brands.add(4L);
        List<Long> categories = new ArrayList<>();
        categories.add(1L);
        categories.add(2L);
        categories.add(3L);

        List<ProductInfoDTO> products = productRepository.searchProductAllSize
                (brands, categories, 0L, 9999999L, 3, 0 );
        products.forEach(System.out::println);
    }

    @Test
    void countProductAllSize_Test(){
        List<Long> brands = new ArrayList<>();
        brands.add(1L);
        brands.add(2L);
        brands.add(3L);
        brands.add(4L);
        List<Long> categories = new ArrayList<>();
        categories.add(1L);
        categories.add(2L);
        categories.add(3L);
        int count = productRepository.countProductAllSize(brands, categories, 0L, 9999999L);
        System.out.println(count);
    }

    @Test
    void searchProductBySize_Test(){
        List<Long> brands = new ArrayList<>();
        brands.add(1L);
        brands.add(2L);
        brands.add(3L);
        brands.add(4L);
        List<Long> categories = new ArrayList<>();
        categories.add(1L);
        categories.add(2L);
        categories.add(3L);
        List<Integer> sizes = new ArrayList<>();
        sizes.add(35);
        sizes.add(36);
        sizes.add(37);
        sizes.add(38);
        sizes.add(39);
        sizes.add(40);
        sizes.add(41);
        sizes.add(42);

        List<ProductInfoDTO> products = productRepository.searchProductBySize
                (brands, categories, 0L, 9999999L,sizes, 3, 0 );
        products.forEach(System.out::println);

    }
    @Test
    void countProductBySize_Test(){
        List<Long> brands = new ArrayList<>();
        brands.add(1L);
        brands.add(2L);
        brands.add(3L);
        brands.add(4L);
        List<Long> categories = new ArrayList<>();
        categories.add(1L);
        categories.add(2L);
        categories.add(3L);
        List<Integer> sizes = new ArrayList<>();
        sizes.add(35);
        sizes.add(36);
        sizes.add(37);
        sizes.add(38);
        sizes.add(39);
        sizes.add(40);
        sizes.add(41);
        sizes.add(42);
        int count = productRepository.countProductBySize(brands, categories, 0L, 9999999L, sizes);
        System.out.println(count);
    }

    @Test
    void searchProductByKeyword_Test(){
        List<ProductInfoDTO> products = productRepository.searchProductByKeyword("super", 5, 0);
        products.forEach(System.out::println);

        List<ProductInfoDTO> products_1 = productRepository.searchProductByKeyword("giày nam", 5, 0);
        products_1.forEach(System.out::println);
    }

    @Test
    void getProductById_test(){
        Product product = productService.getProductById("4iKReQ");
        System.out.println(product);
    }




}
