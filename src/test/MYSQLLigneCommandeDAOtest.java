package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import dao.MYSQLLigneCommandeDAO;
import pojo.LigneCommande;

class MYSQLLigneCommandeDAOtest {

	@Test
	public void testCreate() {
		MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
        test.create(new LigneCommande(5,7,3,50.0));
	}

	@Test
	public void testUpdate() {
		MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
        test.update(new LigneCommande(5,7));
	}

	@Test
	public void testDelete() {
		MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
        test.delete(new LigneCommande(5,7));
	}

	@Test
	public void testgetById() {
		MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
		test.create(new LigneCommande(5,7,3,50.0));
		assertEquals(test.getById(5), 7);
	}
	
}
