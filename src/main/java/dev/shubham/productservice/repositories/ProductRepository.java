package dev.shubham.productservice.repositories;

import dev.shubham.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdIs(long id);
    Product save(Product product);

    List<Product> findAll();
    Optional<Product> findByTitle(String title);
    @Query("select p from product p where (p.title = :title and p.category.title = :categoryTitle)")
    Optional<Product> findByTitleAndCategory(@Param("title") String title,@Param("categoryTitle") String categoryTitle);
    @Query(value = "select * from product where product.category_id = :categoryId", nativeQuery = true)
//    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> getProductsByCategoryId(@Param("categoryId") Long categoryId);

    void deleteById(long id);  // TODO: understand why the method is not used
                               //and default method is getting called from CrudRepository.class
}
