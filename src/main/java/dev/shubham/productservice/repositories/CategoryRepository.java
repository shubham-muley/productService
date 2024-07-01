package dev.shubham.productservice.repositories;

import dev.shubham.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    @Modifying
    void delete(Category category);

    @Modifying
    @Query("UPDATE Category c SET c.title = :#{#category.title}, c.updatedAt = :#{#category.updatedAt} WHERE c.id = :#{#category.id}")
    int updateCategory(@Param("category") Category category);

    Optional<Category> findById(Long Id);

    List<Category> findAll();
}