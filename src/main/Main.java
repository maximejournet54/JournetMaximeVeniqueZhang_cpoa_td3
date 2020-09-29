package main;

import dao.MYSQLCategorieDAO;
import dao.MYSQLClientDAO;
import dao.MYSQLProduitDAO;
import dao.MYSQLCommandeDAO;
import dao.MYSQLLigneCommandeDAO;


import pojo.Categorie;
import pojo.Client;
import pojo.Commande;
import pojo.LigneCommande;
import pojo.Produit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {
        int id_categorie=0;
        int id_client=0;
        int id_produit=0;
        int id_commande=0;
        String date_commande=null;
        
        System.out.println("faites un choix: 1=Categorie, 2=Client, 3=Produit, 4=Commande, 5=LigneCommande");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
   
        //categorie
        if(i==1) {
            System.out.println("choisissez une action: 1= Ajouter, 2= Supprimer, 3= Mettre a jour");
            int choix=sc.nextInt();
            //ajouter
            if (choix==1) {
                System.out.println("Saisir id_categorie, titre et visuel");
                id_categorie=sc.nextInt();
                String titre=sc.next();
                String visuel=sc.next();
                MYSQLCategorieDAO test=new MYSQLCategorieDAO();
                if(!test.create(new Categorie(id_categorie,titre,visuel))){
                    System.out.println("creation impossible");
                }
                else
                System.out.println("creation effectuee"); 
            }
            //supprimer
            if (choix==2) {
                System.out.println("Saisir id_categorie");
                id_categorie=sc.nextInt();
                MYSQLCategorieDAO test=new MYSQLCategorieDAO();
                test.delete(new Categorie(id_categorie));
            }
            //mettre a jour
            if (choix==3) {
                System.out.println("Saisir id_categorie");
                id_categorie=sc.nextInt();
                MYSQLCategorieDAO test=new MYSQLCategorieDAO();
                test.update(new Categorie(id_categorie));
            }
        }   
        
        //client
        else if(i==2) {
            System.out.println("choisissez une action: 1= Ajouter, 2= Supprimer, 3=Mettre a jour");
            int choix2=sc.nextInt();
            //ajouter
            if(choix2==1){
                System.out.println("Saisir id_client, nom, prenom, identifiant, mdp, numero de rue, rue, cp, ville,pays");
                id_client=sc.nextInt();
                String nom=sc.next();
                String prenom=sc.next();
                String id=sc.next();
                String mdp=sc.next();
                int no=sc.nextInt();
                String rue=sc.next();
                int cp=sc.nextInt();
                String ville=sc.next();
                String pays=sc.next();
                MYSQLClientDAO test=new MYSQLClientDAO();
                if(!test.create(new Client(id_client,nom,prenom,id,mdp,no,rue,cp,ville,pays))){
                    System.out.println("creation impossible");
                }
                else
                System.out.println("creation effectuee"); 
            }
            //supprimer
            if (choix2==2) {
                System.out.println("Saisir id_client");
                id_client=sc.nextInt();
                MYSQLClientDAO test=new MYSQLClientDAO();
                test.delete(new Client(id_client));
            }
            //mettre a jour  
            if (choix2==3) {
                System.out.println("Saisir id_client");
                id_client=sc.nextInt();
                MYSQLClientDAO test=new MYSQLClientDAO();
                test.update(new Client(id_client));
            }
        }
            
        //produit
        else if(i==3) {
            System.out.println("choisissez une action: 1= Ajouter, 2= Supprimer, 3=Mettre a jour");
            int choix3=sc.nextInt();
            //ajouter
            if(choix3==1){
                System.out.println("Saisir id_produit, nom, description, tarif, visuel, id_categorie");
                id_produit=sc.nextInt();
                String nom=sc.next();
                String description=sc.next();
                double tarif=sc.nextDouble();
                String visuel=sc.next();
                int id2=sc.nextInt(); 
                MYSQLProduitDAO test=new MYSQLProduitDAO();
                if(!test.create(new Produit(id_produit,nom,description,tarif,visuel,id2))){
                    System.out.println("creation impossible");
                }
                else
                System.out.println("creation effectuee"); 
            }
            //supprimer
            if (choix3==2) {
                System.out.println("Saisir id_produit");
                id_produit=sc.nextInt();
                MYSQLProduitDAO test=new MYSQLProduitDAO();
                test.delete(new Produit(id_produit));
            }
            //mettre a jour
            if (choix3==3) {
                System.out.println("Saisir id_client");
                id_produit=sc.nextInt();
                MYSQLProduitDAO test=new MYSQLProduitDAO();
                test.update(new Produit(id_produit));
            }               
        }

        //commande
        else if(i==4) {
            System.out.println("choisissez une action: 1= Ajouter, 2= Supprimer, 3= Mettre a jour");
            int choix4=sc.nextInt();
            //ajouter
            if (choix4==1) {
                System.out.println("Saisir id_commande");
                id_commande=sc.nextInt();
                System.out.println("Saisir date en format JJ/MM/AAAA");
                date_commande=sc.next();
                System.out.println("Saisir id_client");
                id_client=sc.nextInt();
                MYSQLCommandeDAO test=new MYSQLCommandeDAO();
                if(!test.create(new Commande(id_commande,date_commande,id_client))){
                    System.out.println("creation impossible");
                }
                else
                	System.out.println("creation effectuee"); 
            }
            //supprimer
            if (choix4==2) {
                System.out.println("Saisir id_commande");
                id_commande=sc.nextInt();
                MYSQLCommandeDAO test=new MYSQLCommandeDAO();
                test.delete(new Commande(id_commande));
            }
            //mettre a jour
            if (choix4==3) {
                System.out.println("Saisir id_commande");
                id_commande=sc.nextInt();
                MYSQLCommandeDAO test=new MYSQLCommandeDAO();
                test.update(new Commande(id_commande));
            }
        }   
        
        //LigneCommande
        else if(i==5) {
            System.out.println("choisissez une action: 1= Ajouter, 2= Supprimer, 3= Mettre a jour");
            int choix5=sc.nextInt();
            //ajouter
            if (choix5==1) {
                System.out.println("Saisir id_commande, id_produit, quantite et tarif_unitaire");
                int id_commande2=sc.nextInt();
                int id_produit2=sc.nextInt();
                int qte=sc.nextInt();
                double tarif=sc.nextDouble();
                MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
                if(!test.create(new LigneCommande(id_commande2,id_produit2,qte,tarif))){
                    System.out.println("creation impossible");
                }
                else
                System.out.println("creation effectuee"); 
            }
            //supprimer
            if (choix5==2) {
                System.out.println("Saisir id_commande et id_produit");
                id_commande=sc.nextInt();
                id_produit=sc.nextInt();
                MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
                test.delete(new LigneCommande(id_commande,id_produit));
            }
            //mettre a jour
            if (choix5==3) {
                System.out.println("Saisir id_commande et id_produit");
                id_commande=sc.nextInt();
                id_produit=sc.nextInt();
                MYSQLLigneCommandeDAO test=new MYSQLLigneCommandeDAO();
                test.update(new LigneCommande(id_commande,id_produit));
            }
        }  
    sc.close();
    }

}


