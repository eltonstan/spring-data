package br.com.alura.spring.data.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.UnidadeTrabalho;
import br.com.alura.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {
	
	@Autowired
	private UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	
	private Boolean system = true;
	
	public void inicial(Scanner scanner) {
		
		while(system) {
			System.out.println("Qual acao voce quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			switch (action) {
			case 1:
				this.salvar(scanner);
				break;
			case 2:
				this.atualizar(scanner);
				break;
			case 3:
				this.visualizar();
				break;	
			case 4:
				this.deletar(scanner);
				break;	
			default:
				system = false;
				break;
			}
		}
		
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Nome da unidade");
		String nome = scanner.next();
		
		System.out.println("Endereço da unidade");
		String endereco = scanner.next();
		
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);
		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}
	
	private void atualizar(Scanner scanner) {
		
		System.out.println("Id da unidade");
		int id = scanner.nextInt();
		
		System.out.println("Nome da unidade");
		String nome = scanner.next();
		
		System.out.println("Endereço da unidade");
		String endereco = scanner.next();
		
		Optional<UnidadeTrabalho> unidadeOptional = unidadeTrabalhoRepository.findById(id);
		if (unidadeOptional.isPresent()) {
			UnidadeTrabalho unidadeTrabalho = unidadeOptional.get();
			unidadeTrabalho.setDescricao(nome);
			unidadeTrabalho.setEndereco(endereco);
			unidadeTrabalhoRepository.save(unidadeTrabalho);
		}
		System.out.println("Atualizado");
	}
	
	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> { 
			System.out.println(unidade);
		});
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id da unidade");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}

}
