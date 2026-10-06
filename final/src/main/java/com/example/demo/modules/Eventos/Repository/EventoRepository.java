package com.example.demo.modules.Eventos.Repository;

import com.example.demo.modules.Eventos.model.Entity.EventoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<EventoEntity, Long> {
}
