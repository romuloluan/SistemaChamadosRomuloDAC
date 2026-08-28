package com.example.base.controller;


import com.example.base.dto.UsuarioReqDTO;
import com.example.base.dto.UsuarioRespDTO;
import com.example.base.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/v1/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarUsuario( @RequestBody UsuarioReqDTO usuarioReqDTO){
        usuarioService.salvarUsuario(usuarioReqDTO);
    }

    @PostMapping ("/administradores")
    @ResponseStatus(HttpStatus.CREATED)
    public void salvarAdministrador( @RequestBody UsuarioReqDTO usuarioReqDTO){
        usuarioService.salvarAdministrador(usuarioReqDTO);
    }

    @GetMapping
    public List<UsuarioRespDTO> listarUsuarios(){

        return usuarioService.listarUsuarios();
    }

    @GetMapping("/administradores")
    public List<UsuarioRespDTO> listarAdministradores() {
        return usuarioService.listarAdministradores();
    }

}
