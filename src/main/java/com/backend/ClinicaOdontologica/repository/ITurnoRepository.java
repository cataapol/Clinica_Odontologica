package com.backend.ClinicaOdontologica.repository;
import com.backend.ClinicaOdontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITurnoRepository extends JpaRepository<Turno, Long> {

    void deleteAllById(Long id);
}
