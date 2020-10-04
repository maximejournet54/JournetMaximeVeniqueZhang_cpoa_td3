package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.MYSQLProduitDAO;
import pojo.Produit;

class MYSQLProduitDAOtest {

	@Test
	public void testCreate() {
		MYSQLProduitDAO test=new MYSQLProduitDAO();
        test.create(new Produit(5,"chaussures","ce sont des chaussures",60.99,"nike.jpg",5));
	}

	@Test
	public void testUpdate() {
		MYSQLProduitDAO test=new MYSQLProduitDAO();
        test.update(new Produit(5));
	}

	@Test
	public void testDelete() {
		MYSQLProduitDAO test=new MYSQLProduitDAO();
        test.delete(new Produit(5));
	}

	@Test
	public void testgetById() {
		MYSQLProduitDAO test=new MYSQLProduitDAO();
		test.create(new Produit(5,"chaussures","ce sont des chaussures",60.99,"nike.jpg",5));
		assertEquals(test.getById(5), 5);
	}

}
