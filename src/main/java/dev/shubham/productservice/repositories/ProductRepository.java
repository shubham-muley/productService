package dev.shubham.productservice.repositories;

import dev.shubham.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByIdIs(long id);
    Product save(Product product);
    List<Product> findAll();
}
