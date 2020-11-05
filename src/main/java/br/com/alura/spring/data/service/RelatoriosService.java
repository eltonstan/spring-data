package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual relatorio deseja executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Busca Funcionario por nome");
			System.out.println("2 - Busca Funcionario por nome, salario maior e data de contratação");
			System.out.println("3 - Busca Funcionario por data de contratacao");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				buscaFuncionarioNome(scanner);
				break;
			case 2:
				buscaFuncionarioNomeSalarioMaiorData(scanner);
				break;
			case 3:
				buscaFuncionarioDataContratacao(scanner);
				break;
			default:
				system = false;
				break;
			}
			
		}
		
	}
	
	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("Digite o nome do funcionario");
        String nome = scanner.next();
        
        List<Funcionario> funcionarios = this.funcionarioRepository.findByNome(nome);
        funcionarios.forEach(funcionario -> {
        	System.out.println(funcionario);
        });
		
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Digite nome deseja pesquisar");
        String nome = scanner.next();
        
        System.out.println("Digite data contratacao deseja pesquisar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        
        System.out.println("Digite salario deseja pesquisar");
        Double salario = scanner.nextDouble();
        
        List<Funcionario> funcionarios = this.funcionarioRepository.findNomeDataContratacaoSalarioMaior(nome, salario, localDate);
        funcionarios.forEach(funcionario -> {
        	System.out.println(funcionario);
        });
		
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
        
        System.out.println("Digite data contratacao deseja pesquisar");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);
        
        List<Funcionario> funcionarios = this.funcionarioRepository.findDataContratacaoMaior(localDate);
        funcionarios.forEach(funcionario -> {
        	System.out.println(funcionario);
        });
		
	}
	
}
