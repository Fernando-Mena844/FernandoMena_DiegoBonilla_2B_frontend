package com.example.demo.modules.Eventos.Repository;

import com.example.demo.modules.Eventos.model.Entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventosRepository extends JpaRepository<Evento, Long> {
}
