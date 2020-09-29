package dao;

import java.util.ArrayList;
import java.util.List;

import pojo.Commande;

public class ListeMemoireCommandeDAO extends MYSQLCommandeDAO {
    private static ListeMemoireCommandeDAO instance;
	private List<Commande> donnees;

	public static  ListeMemoireCommandeDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}
		return instance;
	}

	private ListeMemoireCommandeDAO() {
		this.donnees = new ArrayList<Commande>();
		//this.donnees.add(new Commande(10, "27/09/2020", 1));
	}

	public boolean create(Commande objet) {
		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean update(Commande objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
		} else {
			this.donnees.set(idx, objet);
		}
		return true;
	}

	public boolean delete(Commande objet) {
		Commande supprime;
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}

	public Commande getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Commande(id, "27/09/2020", 1));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune commande ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	public ArrayList<Commande> findAll() {
		return (ArrayList<Commande>) this.donnees;
	} 
    
}
