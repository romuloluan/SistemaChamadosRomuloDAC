package com.example.base.dao;
import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioDAO extends JpaRepository<Usuario, Long> {
    List<Usuario> findByPapel(Papel papel);
    Optional<Usuario> findByNome(String nome);
    Boolean existsByEmail(String email);
}
