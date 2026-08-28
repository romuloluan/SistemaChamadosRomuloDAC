package com.example.base.dto;

import com.example.base.model.Prioridade;
import com.example.base.model.Usuario;

public record ChamadoRespDTO(Long id, String titulo, String descricao, String prioridade, String status) {
}
