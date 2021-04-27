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

import br.com.mv.selecao.repository.ProfissionalRepository;

import br.com.mv.selecao.exception.ResourceNotFoundException;

import br.com.mv.selecao.model.Profissional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class ProfissionalController {
	
	@Autowired
	private ProfissionalRepository profissionalRepository;
	
	
	@GetMapping("/profissionais")
	public List<Profissional> getAllProfissionais(){
		return profissionalRepository.findAll();
	}
	
	
	@PostMapping("/profissionais")
	public Profissional criarProfissional(@RequestBody Profissional profissional) {
		return profissionalRepository.save(profissional);
	}
	
	//obter objeto através de rest api
	@GetMapping("/profissionais/{id}")
	public ResponseEntity<Profissional> getProfissionalById(@PathVariable Long id) {
		Profissional profissional = profissionalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um profissional com o id: " + id) );
	return ResponseEntity.ok(profissional);
	
	}
	
	//atualizar profissional por rest api
	@PutMapping("/profissionais/{id}")
	public ResponseEntity<Profissional> atualizarProfissional(@PathVariable Long id, @RequestBody Profissional profissionalDetalhes){
		
		Profissional profissional = profissionalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um profissional com o id: " + id) );
		
		profissional.setNome(profissionalDetalhes.getNome());
		profissional.setEndereco(profissionalDetalhes.getEndereco());
		profissional.setTelCelular(profissionalDetalhes.getTelCelular());
		profissional.setTelFixo(profissionalDetalhes.getTelFixo());
		profissional.setFuncao(profissionalDetalhes.getFuncao());
		
		Profissional atualizarProfissional = profissionalRepository.save(profissional);
		return ResponseEntity.ok(atualizarProfissional);
	}
	
	//apagar profissional via rest api
	@DeleteMapping("/profissionais/{id}")
	public ResponseEntity< Map<String , Boolean>> apagarProfissional(@PathVariable Long id){

		Profissional profissional = profissionalRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um profissional com o id: " + id) );
		
		profissionalRepository.delete(profissional);
		Map<String, Boolean> response = new HashMap<>();
		response.put("apagado", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
//	@GetMapping("/profissionais/{id}")
//	public ModelAndView detalhesProfissional(@PathVariable("id") Long id) {
//		Profissional profissional = profissionalRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um profissional com o id: " + id) );
//		ModelAndView mv = new ModelAndView("profissional/detalhesProissional");
//		mv.addObject("profissional",profissional);
//		return mv;
//	}
//	
//	@PostMapping("/profissionais/{id}")
//	public String detalhesProfissional(@PathVariable("id") Long id, Estabelecimento estabelecimento ) {
//		Profissional profissional = profissionalRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado um profissional com o id: " + id) );
//		estabelecimento.setProfissional(profissional);
//		profissionalRepository.save(profissional);
//		
//		return "redirect:/{id}";
//	}
	
	
	

}
