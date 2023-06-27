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
public class PartidaGoogleDTO implements Serializable {

    private String statusPartida;
    private String tempoPartida;
    private String nomeEquipeCasa;
    private String urlLogoEquipeCasa;
    private String placarEquipeCasa;
    private String golsEquipeCasa;
    private String placarEstendidoEquipeCasa;

    private String nomeEquipeVisitante;
    private String urlLogoEquipeVisitante;
    private String placarEquipeVisitante;
    private String golsEquipeVisitante;
    private String placarEstendidoEquipeVisitante;
}
