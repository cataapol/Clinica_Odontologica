package com.backend.ClinicaOdontologica.repository;

import com.backend.ClinicaOdontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
    void deleteAllById(Long id);
}
