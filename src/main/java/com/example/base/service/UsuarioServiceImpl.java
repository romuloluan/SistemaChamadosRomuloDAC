package com.example.base.service;

import com.example.base.dao.UsuarioDAO;
import com.example.base.dto.UsuarioReqDTO;
import com.example.base.dto.UsuarioRespDTO;
import com.example.base.model.Papel;
import com.example.base.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAO usuarioDAO;

    @Transactional
    @Override
    public void salvarUsuario(UsuarioReqDTO usuarioReqDTO) {     // CADASTRA APENAS USUARIOS COMUNS

        validarUsuario(usuarioReqDTO);

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioReqDTO.nome());
        usuario.setEmail(usuarioReqDTO.email());
        usuario.setSenha(usuarioReqDTO.senha());
        usuario.setPapel(Papel.NORMAL);
        usuarioDAO.save(usuario);
    }

    @Transactional
    public void salvarAdministrador(UsuarioReqDTO usuarioReqDTO) {     // CADASTRA APENAS ADMINISTRADORES

        validarUsuario(usuarioReqDTO);

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioReqDTO.nome());
        usuario.setEmail(usuarioReqDTO.email());
        usuario.setSenha(usuarioReqDTO.senha());
        usuario.setPapel(Papel.ADMIN);
        usuarioDAO.save(usuario);
    }

    @Override
    public List<UsuarioRespDTO> listarUsuarios() { // LISTA TODOS OS USUÁRIOS
        return usuarioDAO.findAll().
                stream().
                map(usuario ->
                        new UsuarioRespDTO(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getPapel().name())).toList();
    }
    @Override
    public List<UsuarioRespDTO> listarAdministradores() { // LISTA APENAS ADMINISTRADORES
        return usuarioDAO.findByPapel(Papel.ADMIN).
                stream().
                map(usuario -> new UsuarioRespDTO(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getPapel().name())).toList();
    }

    public UsuarioRespDTO buscarUsuarioPorNome(String nome) {
        Usuario usuario = usuarioDAO.findByNome(nome).orElseThrow(() -> new RuntimeException("Usuário não encontrado no sistema."));
        return new UsuarioRespDTO(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getPapel().name());
    }

    public void validarUsuario(UsuarioReqDTO usuarioReqDTO) {  // VALIDA SE O EMAIL JÁ EXISTE NO SISTEMA
        if (usuarioDAO.existsByEmail(usuarioReqDTO.email())){
            throw new IllegalArgumentException("Esse e-mail já está sendo utilizado");

        }
    }
}
