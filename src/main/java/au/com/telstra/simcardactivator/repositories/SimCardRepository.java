package au.com.telstra.simcardactivator.repositories;

import au.com.telstra.simcardactivator.entities.SimCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SimCardRepository extends JpaRepository<SimCardEntity, Long> {
    Optional<SimCardEntity> findById(long id);

}
