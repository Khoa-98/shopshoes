package com.vuhien.application.controller.admin;

import com.vuhien.application.entity.*;
import com.vuhien.application.model.request.CreateProductRequest;
import com.vuhien.application.model.request.CreateSizeCountRequest;
import com.vuhien.application.model.request.UpdateFeedBackRequest;
import com.vuhien.application.security.CustomUserDetails;
import com.vuhien.application.service.BrandService;
import com.vuhien.application.service.CategoryService;
import com.vuhien.application.service.ImageService;
import com.vuhien.application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.List;

import static com.vuhien.application.config.Contant.SIZE_VN;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/admin/products")
    public String homePages(Model model,
            @RequestParam(defaultValue = "", required = false) String id,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) String category,
            @RequestParam(defaultValue = "", required = false) String brand,
            @RequestParam(defaultValue = "1", required = false) Integer page) {

        // Lấy danh sách nhãn hiệu
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands", brands);
        // Lấy danh sách danh mục
        List<Category> categories = categoryService.getListCategories();
        model.addAttribute("categories", categories);
        // Lấy danh sách sản phẩm
        Page<Product> products = productService.adminGetListProduct(id, name, category, brand, page);
        model.addAttribute("products", products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", products.getPageable().getPageNumber() + 1);

        return "admin/product/list";
    }

    @GetMapping("/admin/products/create")
    public String getProductCreatePage(Model model) {
        // Lấy danh sách anh của user
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();
        List<String> images = imageService.getListImageOfUser(user.getId());
        model.addAttribute("images", images);

        // Lấy danh sách nhãn hiệu
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands", brands);
        // Lấy danh sách danh mục
        List<Category> categories = categoryService.getListCategories();
        model.addAttribute("categories", categories);

        return "admin/product/create";
    }

    @GetMapping("/admin/products/{slug}/{id}")
    public String getProductUpdatePage(Model model, @PathVariable String id) {

        // Lấy thông tin sản phẩm theo id
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        // Lấy danh sách ảnh của user
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();
        List<String> images = imageService.getListImageOfUser(user.getId());
        model.addAttribute("images", images);

        // Lấy danh sách danh mục
        List<Category> categories = categoryService.getListCategories();
        model.addAttribute("categories", categories);

        // Lấy danh sách nhãn hiệu
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands", brands);

        // Lấy danh sách size
        model.addAttribute("sizeVN", SIZE_VN);

        // Lấy size của sản phẩm
        List<ProductSize> productSizes = productService.getListSizeOfProduct(id);
        model.addAttribute("productSizes", productSizes);

        return "admin/product/edit";
    }

    @GetMapping("/api/admin/products")
    public ResponseEntity<Object> getListProducts(@RequestParam(defaultValue = "", required = false) String id,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) String category,
            @RequestParam(defaultValue = "", required = false) String brand,
            @RequestParam(defaultValue = "1", required = false) Integer page) {
        Page<Product> products = productService.adminGetListProduct(id, name, category, brand, page);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/api/admin/products/{id}")
    public ResponseEntity<Object> getProductDetail(@PathVariable String id) {
        Product rs = productService.getProductById(id);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/api/admin/products")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        Product product = productService.createProduct(createProductRequest);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/api/admin/products/{id}")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody CreateProductRequest createProductRequest,
            @PathVariable String id) {
        productService.updateProduct(createProductRequest, id);
        return ResponseEntity.ok("Sửa sản phẩm thành công!");
    }

    @DeleteMapping("/api/admin/products")
    public ResponseEntity<Object> deleteProduct(@RequestBody String[] ids) {
        productService.deleteProduct(ids);
        return ResponseEntity.ok("Xóa sản phẩm thành công!");
    }

    @DeleteMapping("/api/admin/products/{id}")
    public ResponseEntity<Object> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Xóa sản phẩm thành công!");
    }

    @PutMapping("/api/admin/products/sizes")
    public ResponseEntity<?> updateSizeCount(@Valid @RequestBody CreateSizeCountRequest createSizeCountRequest) {
        productService.createSizeCount(createSizeCountRequest);

        return ResponseEntity.ok("Cập nhật thành công!");
    }

    @PutMapping("/api/admin/products/{id}/update-feedback-image")
    public ResponseEntity<?> updatefeedBackImages(@PathVariable String id,
            @Valid @RequestBody UpdateFeedBackRequest req) {
        productService.updatefeedBackImages(id, req);

        return ResponseEntity.ok("Cập nhật thành công");
    }

}
