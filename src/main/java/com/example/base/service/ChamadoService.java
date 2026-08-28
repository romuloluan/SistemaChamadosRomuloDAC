package com.example.base.service;

import com.example.base.dto.ChamadoReqDTO;
import com.example.base.dto.ChamadoRespDTO;

import java.util.List;

public interface ChamadoService {
    void cadastrarChamado(ChamadoReqDTO chamadoReqDTO, Long idUsuario);
    List<ChamadoRespDTO> buscarChamadosPorUsuario(Long idUsuario);
    List<ChamadoRespDTO> buscarChamadosPorStatus(String status, Long idUsuario);

}
