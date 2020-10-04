package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.MYSQLCommandeDAO;
import pojo.Commande;

class MYSQLCommandeDAOtest {

	@Test
	public void testCreate() {
		MYSQLCommandeDAO test=new MYSQLCommandeDAO();
        test.create(new Commande(5,"12/12/2020",5));
	}

	@Test
	public void testUpdate() {
		MYSQLCommandeDAO test=new MYSQLCommandeDAO();
        test.update(new Commande(5));
	}

	@Test
	public void testDelete() {
		MYSQLCommandeDAO test=new MYSQLCommandeDAO();
        test.delete(new Commande(5));
	}

	@Test
	public void testgetById() {
		MYSQLCommandeDAO test=new MYSQLCommandeDAO();
		test.create(new Commande(5,"12/12/2020",5));
		assertEquals(test.getById(5), 5);
	}
}
