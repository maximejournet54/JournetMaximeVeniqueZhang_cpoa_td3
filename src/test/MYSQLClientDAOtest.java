package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojo.Client;
import dao.MYSQLCategorieDAO;
import dao.MYSQLClientDAO;
import dao.DAOFactory;
import connexion.Persistance;

public class MYSQLClientDAOtest {
	private Client c;

	@BeforeEach
		public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
		c=new Client(1,"aa", "aaa");
		MYSQLClientDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
		MYSQLClientDAO.getInstance().delete(c);
    }
	
	@Test
	public void testSelectExiste() throws Exception {
		int id=c.getId_client();
		Client cBdd=MYSQLClientDAO.getInstance().getById(id);
		assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
	    try {
			DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(c.getId_client());
			}catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}	
	}

	@Test
	public void testCreate() throws Exception {
	    Client c2 = new Client(1,"aa", "aaa");
		try {   
			MYSQLClientDAO.getInstance().create(c2);
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"aa");
		assertEquals(c.getPrenom(),"aaa");
		MYSQLClientDAO.getInstance().delete(c2);	
	}

	@Test
	public void testDelete() throws Exception {
	    Client c2 =new Client(1,"aa", "aaa");
	    MYSQLClientDAO.getInstance().create(c2);	
		int idd = c2.getId_client();
		assertTrue(MYSQLClientDAO.getInstance().delete(c2));
		Client cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(idd);
		assertNull(cl);
		assertFalse(MYSQLClientDAO.getInstance().delete(cl));	
	}
	
	@Test
	public void testUpdate() throws Exception {
		Client c2= new Client(c.getId_client(),"bb","bbb");
		DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().update(c2);
		Client c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(c2.getId_client());
		assertEquals("bb", c3.getNom());
		assertEquals("bbb", c3.getPrenom());
		MYSQLClientDAO.getInstance().delete(c2);
		MYSQLClientDAO.getInstance().delete(c3);

	}
}
