package dev.shubham.productservice.repositories;

import dev.shubham.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByTitle(String title);
}