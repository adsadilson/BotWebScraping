package br.com.apssystem.brasileiraoapi.controller;

import br.com.apssystem.brasileiraoapi.bot.BotCampeonatoMain;
import br.com.apssystem.brasileiraoapi.service.CampeonatoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/campeonatos")
@AllArgsConstructor
public class CampeonatoController {

    private CampeonatoService campeonatoService;
    private BotCampeonatoMain bot;

    @PostMapping
    public ResponseEntity<?> save() {
        List<String> campeonatos = bot.getListaDeCampeonatos();
        String dto = campeonatoService.saveDTO(campeonatos);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
