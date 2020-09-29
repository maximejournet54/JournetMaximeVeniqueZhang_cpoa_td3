package dao;

import java.util.ArrayList;
import java.util.List;

import pojo.Produit;

public class ListeMemoireProduitDAO extends MYSQLProduitDAO {
    private static ListeMemoireProduitDAO instance;
	private List<Produit> donnees;

	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}
		return instance;
	}

	private ListeMemoireProduitDAO() {
		this.donnees = new ArrayList<Produit>();
		this.donnees.add(new Produit(7, "Chaussettes", "ce sont des chaussettes", 10.0,"chaussetts.png", 3));
	}

	public boolean create(Produit objet) {
		objet.setId(3);
		// Ne fonctionne que si l'objet métier est bien fait...
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}

	public boolean update(Produit objet) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un produit inexistant");
		} else {
			this.donnees.set(idx, objet);
		}
		return true;
	}

	public boolean delete(Produit objet) {
		Produit supprime;
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un produit inexistant");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}

	public Produit getById(int id) {
		// Ne fonctionne que si l'objet métier est bien fait...
		int idx = this.donnees.indexOf(new Produit(7, "Chaussettes", "ce sont des chaussettes", 10.0,"chaussetts.png", 3));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune produit ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}

	public ArrayList<Produit> findAll() {
		return (ArrayList<Produit>) this.donnees;
	}
}
