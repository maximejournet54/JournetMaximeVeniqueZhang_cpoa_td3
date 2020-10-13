package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pojo.Client;
import pojo.Commande;
import dao.MYSQLClientDAO;
import dao.MYSQLCommandeDAO;
import dao.DAOFactory;
import connexion.Persistance;

public class MYSQLCommandeDAOtest {
	private Commande c;
	private Client client;   

    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
		client=new Client("Jack","Ma");
		c=new Commande("2020-01-01" ,client);
		MYSQLCommandeDAO.getInstance().create(c);
		MYSQLClientDAO.getInstance().create(client);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
		MYSQLCommandeDAO.getInstance().delete(c);
		MYSQLClientDAO.getInstance().delete(client);
    }
    
	@Test
	public void testSelectExiste() throws Exception {
		assertNotNull(c);
 }
	@Test
	public void testGetbyid() throws Exception {
	    try {
			DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c.getId());
		}catch(Exception e) {
	    	fail("erreur de getbyid");
	    }
	    	
	}

	@Test
	public void testCreate() throws Exception {
		client=new Client("JOURNET","Maxime");
		 c=new Commande("2020-01-01",client);
		try {
	    MYSQLClientDAO.getInstance().create(client);	
		MYSQLCommandeDAO.getInstance().create(c);
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(c.getId_client().getNom(),"JOURNET");
		assertEquals(c.getDate_commande().toString(),"2020-01-01");
		MYSQLCommandeDAO.getInstance().delete(c);
		MYSQLClientDAO.getInstance().delete(client);
	}
	@Test
	public void testDelete() throws Exception {
	    try {
			MYSQLCommandeDAO.getInstance().delete(c);
		}catch(Exception e) {
			fail("L'abonnement n'a pas ete supprimer");
			}	
	}
	
	@Test
	public void testUpdate() throws Exception {
		client=new Client("JOURNET","Maxime");
		c=new Commande("2020-01-01",client);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().update(c);
		assertEquals(c.getId_client().getNom(),"JOURNET");
		assertEquals(c.getDate_commande().toString(),"2020-01-01");
		MYSQLCommandeDAO.getInstance().delete(c);
		MYSQLClientDAO.getInstance().delete(client);
	}
}
