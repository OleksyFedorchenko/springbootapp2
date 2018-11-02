package ua.logos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.logos.entity.CategoryEntity;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
}
