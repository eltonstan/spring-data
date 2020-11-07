package br.com.alura.spring.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {
	
	List<Funcionario> findByNome(String nome);
	
	@Query("SELECT f FROM Funcionario f where f.nome = :nome and f.salario >= :salario and f.dataContratacao = :dataContratacao")
	List<Funcionario> findNomeDataContratacaoSalarioMaior(String nome, 
			Double salario, LocalDate dataContratacao);
	
	@Query(value = "SELECT * FROM funcionarios f where f.data_contratacao >= :data", nativeQuery = true)
	List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
	@Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
	List<FuncionarioProjecao> findFuncionarioSalario();
	
}
