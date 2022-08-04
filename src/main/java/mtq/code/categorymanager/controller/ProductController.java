package mtq.code.categorymanager.controller;

import lombok.RequiredArgsConstructor;
import mtq.code.categorymanager.entity.Category;
import mtq.code.categorymanager.entity.Product;
import mtq.code.categorymanager.repository.CategoryRepo;
import mtq.code.categorymanager.repository.ProductRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @GetMapping
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody Product product) {
        Product exists = productRepo.findByProName(product.getProName());
        if (exists != null) {
            throw new IllegalStateException("This product is exists !");
        }
        productRepo.save(product);
    }

    @PostMapping("/addToCategory")
    public ResponseEntity<?> addToCategory(@RequestBody AddProductToCategoryForm form) {
        Product productEntity = productRepo.findByProName(form.getProName());
        Category categoryEntity = categoryRepo.findByCateName(form.getCateName());
        categoryEntity.getProduct().add(productEntity);
        categoryRepo.save(categoryEntity);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductByName(@RequestBody Product product) {
        return ResponseEntity.ok().body(productRepo.findProductByProName(product.getProName()));
    }
}
