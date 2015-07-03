package it.univr.is.database;

import static org.junit.Assert.*;
import it.univr.is.entity.Utente;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestDatasource {

	private static Datasource d;
	private static Utente u;
	
	@BeforeClass
	public static void inizializza(){
		d = new Datasource();
		u = new Utente();
		u.setNome("nomeTest");
		u.setCognome("cognomeTest");
		u.setCap("33333");
		u.setEmail("test@test.test");
		u.setDataisc("2015-03-05");
		u.setPassword("testPassword");
		u.setVia("viaTest");
		u.setCivico(1);
		u.setCitta("cittaTest");
		u.setProvincia("tt");
		u.setRuolo(0);
	}
	
	@Test
	public void eliminaUtenteTest(){
		d.checkAndSubscribe(u);
		d.eliminaUtente(u);
		assertTrue(d.checkMail(u.getEmail()));
	}
	
	@Test
	public void checkAndSubscribeTest() {
		assertTrue(d.checkAndSubscribe(u));
		assertFalse(d.checkAndSubscribe(u));
		d.eliminaUtente(u);
	}
	
	@Test
	public void checkMailTest(){
		assertTrue(d.checkMail(u.getEmail()));
		d.checkAndSubscribe(u);
		assertFalse(d.checkMail(u.getEmail()));
		d.eliminaUtente(u);
	}

}
