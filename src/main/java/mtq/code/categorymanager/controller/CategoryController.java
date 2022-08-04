package mtq.code.categorymanager.controller;

import lombok.RequiredArgsConstructor;
import mtq.code.categorymanager.entity.Category;
import mtq.code.categorymanager.repository.CategoryRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepo categoryRepo;
    @GetMapping
    public List<Category> getCategories() {
        return categoryRepo.findAll();
    }
    @PostMapping("/save")
    public ResponseEntity<String> saveCategories(@RequestBody List<Category> cateData) {
        Category category = new Category();
        Optional<Category> exists = categoryRepo.findCategoryByCateName(category.getCateName());
        if (exists.isPresent()) {
            throw new IllegalStateException("This category is exists");
        }
        categoryRepo.saveAll(cateData);
        return ResponseEntity.ok("Data saved");
    }

}
