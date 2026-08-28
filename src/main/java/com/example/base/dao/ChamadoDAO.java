package com.example.base.dao;

import com.example.base.model.Chamado;
import com.example.base.model.Status;
import com.example.base.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChamadoDAO extends JpaRepository<Chamado, Long> {
    List<Chamado> findByUsuario_Id(Long id);
    List<Chamado> findByUsuario_IdAndStatus(Long id, Status status);
}
