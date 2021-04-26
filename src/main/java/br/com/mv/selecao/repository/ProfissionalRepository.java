package br.com.mv.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.selecao.model.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{

}
