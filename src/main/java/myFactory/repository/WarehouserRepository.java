package myFactory.repository;

import myFactory.model.entities.Warehouser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WarehouserRepository extends JpaRepository<Warehouser, UUID> {
}
