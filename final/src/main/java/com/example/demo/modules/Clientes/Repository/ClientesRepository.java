package com.example.demo.modules.Clientes.Repository;

import com.example.demo.modules.Clientes.model.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Cliente, Long>{
}
