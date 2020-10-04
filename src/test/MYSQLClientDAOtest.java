package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import dao.MYSQLClientDAO;
import pojo.Client;
class MYSQLClientDAOtest {

	@Test
	public void testCreate() {
		MYSQLClientDAO test=new MYSQLClientDAO();
        test.create(new Client(5,"Journet","Maxime","journet9u","mdp",4,"rue de Metz",57000,"Metz","France"));
	}

	@Test
	public void testUpdate() {
		MYSQLClientDAO test=new MYSQLClientDAO();
        test.update(new Client(5));
	}

	@Test
	public void testDelete() {
		MYSQLClientDAO test=new MYSQLClientDAO();
        test.delete(new Client(5));
	}

	@Test
	public void testgetById() {
		MYSQLClientDAO test=new MYSQLClientDAO();
		test.create(new Client(5,"Journet","Maxime","journet9u","mdp",4,"rue de Metz",57000,"Metz","France"));
		assertEquals(test.getById(5), 5);
	}

}
