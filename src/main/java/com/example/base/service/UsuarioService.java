package com.example.base.service;
import com.example.base.dto.UsuarioReqDTO;
import com.example.base.dto.UsuarioRespDTO;
import java.util.List;

public interface UsuarioService {
    void salvarUsuario(UsuarioReqDTO usuarioReqDTO);
    void salvarAdministrador(UsuarioReqDTO usuarioReqDTO);
    List<UsuarioRespDTO> listarUsuarios();
    List<UsuarioRespDTO> listarAdministradores();

}
