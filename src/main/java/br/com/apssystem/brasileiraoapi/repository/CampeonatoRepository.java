package br.com.apssystem.brasileiraoapi.repository;

import br.com.apssystem.brasileiraoapi.entity.Campeonato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato,Long> {
    Optional<Campeonato> findByNome(String nome);
}
