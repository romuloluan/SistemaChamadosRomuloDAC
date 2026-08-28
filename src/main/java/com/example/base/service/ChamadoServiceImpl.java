package com.example.base.service;

import com.example.base.dao.ChamadoDAO;
import com.example.base.dao.UsuarioDAO;
import com.example.base.dto.ChamadoReqDTO;
import com.example.base.dto.ChamadoRespDTO;
import com.example.base.model.Chamado;
import com.example.base.model.Prioridade;
import com.example.base.model.Status;
import com.example.base.model.Usuario;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoDAO chamadoDAO;

    private final UsuarioDAO usuarioDAO;

    @Transactional
    @Override
    public void cadastrarChamado(ChamadoReqDTO chamadoReqDTO, Long idUsuario) {
        Chamado chamado = new Chamado();
        chamado.setTitulo(chamadoReqDTO.titulo());
        chamado.setDescricao(chamadoReqDTO.descricao());
        chamado.setPrioridade(
                switch(chamadoReqDTO.prioridade().toUpperCase()) {
                  case "BAIXA" -> Prioridade.BAIXA;
                  case "MEDIA" -> Prioridade.MEDIA;
                  case "ALTA" -> Prioridade.ALTA;
                  default -> Prioridade.BAIXA;
                });
        chamado.setStatus(Status.ABERTO);

        chamado.setUsuario(usuarioDAO.findById(idUsuario).orElseThrow());
        chamadoDAO.save(chamado);
    }

    @Override
    public List<ChamadoRespDTO> buscarChamadosPorUsuario(Long idUsuario) {
        List<Chamado> chamados = chamadoDAO.findByUsuario_Id(idUsuario);
        return chamados.stream().map(chamado -> new ChamadoRespDTO(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(),
                chamado.getPrioridade().name(), chamado.getStatus().name())).toList();
    }

    @Override
    public List<ChamadoRespDTO> buscarChamadosPorStatus(String status, Long idUsuario) {
        List<Chamado> chamados = chamadoDAO.findByUsuario_IdAndStatus(idUsuario, Status.valueOf(status.toUpperCase()));
        return chamados.stream().
                map( chamado -> new ChamadoRespDTO(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(), chamado.getPrioridade().name(), chamado.getStatus().name())).toList();
    }



}
