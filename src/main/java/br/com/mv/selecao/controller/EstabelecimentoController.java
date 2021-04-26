package br.com.mv.selecao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mv.selecao.exception.ResourceNotFoundException;
import br.com.mv.selecao.model.Estabelecimento;

import br.com.mv.selecao.repository.EstabelecimetoRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class EstabelecimentoController {
	
	@Autowired
	private EstabelecimetoRepository estabelecimentoRepository;
	
	@GetMapping("/estabelecimentos")
	public List<Estabelecimento> getAllEstabelecimentos(){
		return estabelecimentoRepository.findAll();
	}
	
	@PostMapping("/estabelecimentos")
	public Estabelecimento criarEstabelecimento(@RequestBody Estabelecimento estabelecimento) {
		return estabelecimentoRepository.save(estabelecimento);
	}
	
	//obter objeto através de rest api
		@GetMapping("/estabelecimentos/{id}")
		public ResponseEntity<Estabelecimento> getEstabelecimentoById(@PathVariable Long id) {
			Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um estabelecimento com o id: " + id) );
		return ResponseEntity.ok(estabelecimento);
		
		}
	
		//atualizar estabelecimento por rest api
		@PutMapping("/estabelecimentos/{id}")
		public ResponseEntity<Estabelecimento> atualizarEstabelecimento(@PathVariable Long id, @RequestBody Estabelecimento estabelecimentoDetalhes){
			
			Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um estabelecimento com o id: " + id) );
			
			estabelecimento.setNome(estabelecimentoDetalhes.getNome());
			estabelecimento.setEndereco(estabelecimentoDetalhes.getEndereco());
			estabelecimento.setTelefone(estabelecimentoDetalhes.getTelefone());
	
			
			Estabelecimento atualizarEstabelecimento = estabelecimentoRepository.save(estabelecimento);
			return ResponseEntity.ok(atualizarEstabelecimento);
		}
		
		//apagar profissional via rest api
		@DeleteMapping("/estabelecimentos/{id}")
		public ResponseEntity< Map<String , Boolean>> apagarEstabelecimento(@PathVariable Long id){

			Estabelecimento estabelecimento = estabelecimentoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um Estabelecimento com o id: " + id) );
			
			estabelecimentoRepository.delete(estabelecimento);
			Map<String, Boolean> response = new HashMap<>();
			response.put("apagado", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
}
