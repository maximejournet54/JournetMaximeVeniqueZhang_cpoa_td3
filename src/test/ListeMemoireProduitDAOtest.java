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
import dao.ListeMemoireProduitDAO;

import pojo.Produit;

public class ListeMemoireProduitDAOtest {
	private Produit p;

	@Before
    public void Setup() throws Exception {	
		p =new Produit(5, "zzz", "aaa",(float) 1.0,"aa.png",10);
		ListeMemoireProduitDAO.getInstance().create(p);
	}
	
	@Test
	public void testSelectExiste() throws Exception {
		int id=p.getId_produit();
		Produit p2=ListeMemoireProduitDAO.getInstance().getById(id);
		assertNotNull(p2);
 }

	@Test
	public void testCreate() {
		//assertEquals(c.getId(),1);
		assertEquals(p.getNom(),"zzz");
		assertEquals(p.getDescription(),"aaa");
		assertEquals(p.getTarif(),1.0,1.0); 
		assertEquals(p.getVisuel(),"aaa.png");
		assertEquals(p.getId_categorie(),10);
	}

	@Test
	public void testUpdate() throws Exception{
		Produit p2= new Produit(p.getId_produit(),"fdfdfd","dfdfdfd",(float)2.0,"dfsd.png",12);
		((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getProduitDAO().update(p2);
		Produit p3 = ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getProduitDAO().getById(p2.getId_produit());
		
		assertEquals("fdfdfd", p3.getNom());
		assertEquals("dfdfdfd", p3.getDescription());
		assertEquals((float)1,0, p3.getTarif());
		assertEquals("dfsd.png", p3.getVisuel());
		assertEquals(2, p3.getId_categorie());
	}

	@Test
	public void testDelete() throws Exception {
		assertTrue((ListeMemoireProduitDAO.getInstance().delete(p)), "");
		int id = p.getId_produit();

		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getProduitDAO().getById(id);
		fail("Le produit existe toujours");
		}catch(Exception e){

		}
		
		try {
		ListeMemoireProduitDAO.getInstance().delete(p);
		fail("Le produit existe toujours");
		}
		catch (Exception e){
		    
		}	
	}

	@Test
	public void testgetById() throws Exception {
		try {
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getProduitDAO().getById(p.getId_produit());
		} catch(Exception e) {
				fail("Erreur lors de la récupération");
			}
	}

	@Test
	public void testfindAll() throws Exception {
			Produit c2=new Produit(1, "zzz", "aaa",(float) 1.0,"aa.png",10);
			ListeMemoireProduitDAO l = (ListeMemoireProduitDAO) ((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getProduitDAO();
			ArrayList<Produit> ar = new ArrayList<Produit>(l.findAll());
			ar.add(c2);
			l.create(c2);	
			assertEquals(l.findAll(), ar);
			((DAOFactory) DAOFactory.getDAOFactory(Persistance.LISTE_MEMOIRE)).getProduitDAO().delete(p);
	}

}
