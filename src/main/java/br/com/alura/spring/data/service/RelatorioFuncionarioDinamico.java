package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.specification.FuncionarioSpecification;

@Service
public class RelatorioFuncionarioDinamico {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public void inicial(Scanner scanner) {
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		
		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		
		if (salario == 0) {
			salario = null;
		}
		
		System.out.println("Digite a data contratação");
		String data = scanner.next();
		
		LocalDate dataContratacao = null; 
		if (!data.equalsIgnoreCase("NULL")) {
			dataContratacao = LocalDate.parse(data, formatter);
		}
		
		List<Funcionario> funcionarios = funcionarioRepository.findAll(
				Specification.where(
					FuncionarioSpecification.nome(nome))
					.or(FuncionarioSpecification.cpf(cpf))
					.or(FuncionarioSpecification.salario(salario))
					.or(FuncionarioSpecification.dataContratacao(dataContratacao))
				);
		
		funcionarios.forEach(System.out::println);
			
	}

}
