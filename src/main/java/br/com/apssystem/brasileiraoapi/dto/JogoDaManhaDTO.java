package br.com.apssystem.brasileiraoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JogoDaManhaDTO implements Serializable {

    private String id;
    private String horaPartida;
    private String nomeEquipeCasa;
    private String urlLogoEquipeCasa;
    private String dataPartida;
    private String nomeEquipeVisitante;
    private String urlLogoEquipeVisitante;

}
