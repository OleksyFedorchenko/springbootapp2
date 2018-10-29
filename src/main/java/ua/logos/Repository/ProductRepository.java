package ua.logos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.ProductEntity;
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
