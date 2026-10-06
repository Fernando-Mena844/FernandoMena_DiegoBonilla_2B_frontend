package com.example.demo.modules.Salones.Repository;

import com.example.demo.modules.Salones.model.Entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {
}
