package mtq.code.categorymanager.repository;

import mtq.code.categorymanager.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    Category findByCateName(String cateName);
    Optional<Category> findCategoryByCateName(String cateName);
}