package dev.shubham.productservice.repositories;

import dev.shubham.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdIs(long id);
    Product save(Product product);
    List<Product> findAll();
    Product findByTitle(String title);
    @Query("select p from Product p where (p.title = :title and p.category.title = :categoryTitle)")
    Product findByTitleAndCategory(String title, String categoryTitle);
}
