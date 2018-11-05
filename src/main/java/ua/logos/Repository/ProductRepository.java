package ua.logos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.logos.entity.ProductEntity;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

//    @Query("SELECT p FROM ProductEntity p WHERE p.name=:name")    //old variant
//    ProductEntity findProductByName(@Param("name") String name);

    ProductEntity findByName(String name);   //new variant

    List<ProductEntity> findByNameContainingAndPriceIsBefore(String name, BigDecimal price);

}
