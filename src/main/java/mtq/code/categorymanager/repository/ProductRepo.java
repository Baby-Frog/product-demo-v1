package mtq.code.categorymanager.repository;

import mtq.code.categorymanager.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByProName(String proName);

    @Query("SELECT pro FROM Product pro WHERE pro.proName = ?1")
    List<Product> findProductByProName(String proName);
}
