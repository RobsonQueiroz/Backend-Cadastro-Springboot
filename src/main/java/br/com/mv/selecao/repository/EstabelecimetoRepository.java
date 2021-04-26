package br.com.mv.selecao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mv.selecao.model.Estabelecimento;


@Repository
public interface EstabelecimetoRepository extends JpaRepository<Estabelecimento, Long> {

}
