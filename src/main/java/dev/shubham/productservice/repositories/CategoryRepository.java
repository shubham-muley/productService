package dev.shubham.productservice.repositories;

import dev.shubham.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);
    void deleteById(Long Id);
    @Query("UPDATE Category c SET c.title = :title WHERE c.id = :id")
    int updateCategoryTitle(@Param("id") Long id, @Param("title") String title);
    Optional<Category> findById(Long Id);
}