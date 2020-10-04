package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dao.MYSQLCategorieDAO;
import pojo.Categorie;
class MYSQLCategorieDAOtest {

	@Test
	public void testCreate() {
		MYSQLCategorieDAO test=new MYSQLCategorieDAO();
        test.create(new Categorie(5,"chaussures.png", "chaussures.png"));
	}

	@Test
	public void testUpdate() {
		MYSQLCategorieDAO test=new MYSQLCategorieDAO();
        test.update(new Categorie(5));
	}

	@Test
	public void testDelete() {
		MYSQLCategorieDAO test=new MYSQLCategorieDAO();
        test.delete(new Categorie(5));
	}

	@Test
	public void testgetById() {
		MYSQLCategorieDAO test=new MYSQLCategorieDAO();
		test.create(new Categorie(5,"chaussures.png", "chaussures.png"));
		assertEquals(test.getById(5), 5);
	}


}
