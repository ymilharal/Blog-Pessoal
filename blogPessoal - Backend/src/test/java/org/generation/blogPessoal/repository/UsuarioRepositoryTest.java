package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.generation.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) 
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
    
	@Autowired
	private UsuarioRepository usuarioRep;
	
	@BeforeAll
	void start(){

		usuarioRep.save(new Usuario(0L, "Yasmim Marques", "ysa@gmail.com", "12345", "url"));
		
		usuarioRep.save(new Usuario(0L, "Gabriel Marques", "gab@gmail.com", "12345678", "url"));
		
		usuarioRep.save(new Usuario(0L, "Jaqueline Feitosa", "jaque@gmail.com", "123456", "url"));

        usuarioRep.save(new Usuario(0L, "Ayla Marques", "aylinda@gmail.com", "1234567", "url"));

	}

	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {

		Optional<Usuario> usuario = usuarioRep.findByUsuario("jaque@gmail.com");
		assertTrue(usuario.get().getUsuario().equals("jaque@gmail.com"));
	}

	@Test
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {

		List<Usuario> listaDeUsuarios = usuarioRep.findAllByNomeContainingIgnoreCase("Marques");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Yasmim Marques"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Gabriel Marques"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Ayla Marques"));
		
	}

}