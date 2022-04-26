package ma.emsi.patientsmvc.repositories;

import ma.emsi.patientsmvc.entities.Nurse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse,Long> {
    Page<Nurse> findByNomContains(String kw , Pageable pageable);
}
