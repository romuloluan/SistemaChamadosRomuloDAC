package com.example.base.controller;

import com.example.base.dto.ChamadoReqDTO;
import com.example.base.dto.ChamadoRespDTO;
import com.example.base.service.ChamadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/v1/usuarios/{usuarioId}/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarChamado( @RequestBody ChamadoReqDTO chamadoReqDTO, @PathVariable Long usuarioId){
        chamadoService.cadastrarChamado(chamadoReqDTO, usuarioId);
    }

    @GetMapping
    public List<ChamadoRespDTO> buscarChamadosPorUsuario(@PathVariable Long usuarioId){
        return chamadoService.buscarChamadosPorUsuario(usuarioId);
    }

    @GetMapping("/abertos")
    public List<ChamadoRespDTO> buscarChamadosPorStatus(@PathVariable Long usuarioId){
        return chamadoService.buscarChamadosPorStatus("aberto", usuarioId);

    }

}
