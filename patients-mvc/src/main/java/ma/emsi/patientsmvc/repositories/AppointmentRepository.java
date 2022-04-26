package ma.emsi.patientsmvc.repositories;

import ma.emsi.patientsmvc.entities.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
    Page<Appointment> findByPatientnameContains(String kw , Pageable pageable);
}
