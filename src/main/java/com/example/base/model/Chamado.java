package com.example.base.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Chamado {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "id_usuario", nullable = false)
    private Usuario usuario;


}
