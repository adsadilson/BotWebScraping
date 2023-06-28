package br.com.apssystem.brasileiraoapi.service;

import br.com.apssystem.brasileiraoapi.entity.Campeonato;
import br.com.apssystem.brasileiraoapi.repository.CampeonatoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CampeonatoService {

    private CampeonatoRepository repository;

    public String save(List<Campeonato> obj) {
        AtomicInteger count = new AtomicInteger(0);
        obj.forEach(it -> {
            if (!campeonatoExistente(it)) {
                repository.save(it);
                count.getAndIncrement();
            }
        });
        if (count.get() > 0) {
            String msg = String.format("%s Registro(s) salvo com sucesso!", count.get());
            log.info(msg);
            return msg;
        }
        return "Nenhum registro cadastrado!";
    }

    public String saveDTO(List<String> list) {
        List<Campeonato> collect = list.stream().map(it -> Campeonato
                .builder()
                .nome(it.toString())
                .build()).collect(Collectors.toList());
       return save(collect);
    }

    public boolean campeonatoExistente(Campeonato obj) {
        Optional<Campeonato> campeonato = repository.findByNome(obj.getNome());
        if (campeonato.isPresent() && !campeonato.get().getId().equals(obj.getId())) {
            log.info("Registro já existente: {}", obj.getNome());
            return true;
        }
        return false;
    }

}
