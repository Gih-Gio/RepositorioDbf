package com.gio.projeto;

import java.util.Calendar;
import java.util.Date;

import com.gio.projeto.model.Perfil;
import com.gio.projeto.repository.PerfilRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetoPraticoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPraticoApplication.class, args);
	}

	//Função para inicializar a base de dados (inserir registros)
	@Bean
	CommandLineRunner initDatabase(PerfilRepository perfilRepository){
		return args -> {
			perfilRepository.deleteAll();

			//criando a data do nascimento para o registro de teste
			Calendar calendar = Calendar.getInstance();
			calendar.set(2002, 10, 3);
			Date dataNascimento = calendar.getTime();

			//criando o registro de teste
			Perfil p = new Perfil();
			p.setDataNasc(dataNascimento);
			p.setEmail("teste@gmai.com");
			p.setSenha("1234");
			p.setEstado(true);
			p.setNivel(45L);
			p.setExperiencia(255988L);
			p.setTotalCookets(78L);

			perfilRepository.save(p);
		};
	}
}
