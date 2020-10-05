package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;

import org.junit.Before;


import java.util.ArrayList;

import connexion.Persistance;

import dao.DAOFactory;
import dao.ListeMemoireClientDAO;

import pojo.Client;

public class ListeMemoireClientDAOtest {

	private Client c;

	@Before
    public void Setup() throws Exception {	
		c =new Client(5,"nom","prenom","identifiant","mdp",5,"voie",57000,"ville","pays");
		ListeMemoireClientDAO.getInstance().create(c);
	}
	
	@Test
	public void testSelectExiste() throws Exception {
		int id=c.getId_client();
		Client c=ListeMemoireClientDAO.getInstance().getById(id);
		assertNotNull(c);
 }

	@Test
	public void testCreate() {
		//assertEquals(c.getId_client(),5);
		assertEquals(c.getNom(),"nom");
		assertEquals(c.getPrenom(),"prenom");
		assertEquals(c.getIdentifiant(),"identifiant");
		assertEquals(c.getMdp(),"mdp");
		assertEquals(c.getRue(),5);
		assertEquals(c.getVoie(),"voie");
		assertEquals(c.getCp(),57000);
		assertEquals(c.getVille(),"ville");
		assertEquals(c.getPays(),"pays");
	}

	@Test
	public void testUpdate() throws Exception{
		Client c2= new Client(c.getId_client(),"nom","prenom","identifiant","mdp",5,"voie",57000,"ville","pays");
		((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getClientDAO().update(c2);
		Client c3 = ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getClientDAO().getById(c2.getId_client());
		
		//assertEquals(5,c.getId_client());
		assertEquals("nom",c3.getNom());
		assertEquals("prenom",c3.getPrenom());
		assertEquals("identifiant",c3.getIdentifiant());
		assertEquals("mdp",c3.getMdp());
		assertEquals(5,c.getRue());
		assertEquals("voie",c3.getVoie());
		assertEquals(57000,c3.getCp());
		assertEquals("ville",c3.getVille());
		assertEquals("pays",c3.getPays());
	}

	@Test
	public void testDelete() throws Exception {
		assertTrue((ListeMemoireClientDAO.getInstance().delete(c)), "");
		int id = c.getId_client();

		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getClientDAO().getById(id);
		fail("Le client existe toujours");
		}catch(Exception e){

		}
		
		try {
		ListeMemoireClientDAO.getInstance().delete(c);
		fail("le client existe toujours");
		}
		catch (Exception e){
		    
		}	
	}

	@Test
	public void testgetById() throws Exception {
		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getClientDAO().getById(c.getId_client());
		} catch(Exception e) {
				fail("Erreur lors de la récupération");
			}
	}

	@Test
	public void testfindAll() throws Exception {
		Client c2=new Client(5,"nom","prenom","identifiant","mdp",5,"voie",57000,"ville","pays");
			ListeMemoireClientDAO l = (ListeMemoireClientDAO) ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getClientDAO();
			ArrayList<Client> ar = new ArrayList<Client>(l.findAll());
			ar.add(c2);
			l.create(c2);	
			assertEquals(l.findAll(), ar);
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getClientDAO().delete(c);
	}

}
