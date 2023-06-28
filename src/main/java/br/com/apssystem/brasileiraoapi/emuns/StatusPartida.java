package br.com.apssystem.brasileiraoapi.emuns;

import lombok.Getter;

@Getter
public enum StatusPartida {
    PARTIDA_NAO_INICIADA,
    PARTIDA_INICIADA,
    PARTIDA_EM_ANDAMENTO,
    PARTIDA_FINALIZADA,
    PARTIDA_PENATIS;
}
