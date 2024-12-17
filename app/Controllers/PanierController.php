<?php
namespace App\Controllers;
use App\Models\Panier;

class PanierController extends BaseController {

    public function index(){
        
        $lesVetements = Panier::all();
        echo "Liste des produits : <br/>";
        foreach ($lesVetements as $vetement) {
            echo $vetement->designation."<br/>";
        }
        echo "<hr>";
        echo "Nombre de Produits : ".$lesVetements->count();
    }

        // Ici, save est utilisé pour une modification (UPDATE)
    public function modProduit(){
        $id=$this->request->getVar('id');
        $px=$this->request->getVar('prix');
        $designation=$this->request->getVar('designation');
    //-----> validation et divers contrôles omis <------
        $produit=Panier::find($id);
        $produit->designation=$designation;
        $produit->prix=$px;
        $produit->save();
    }

    // Ici, save est utilisé pour une création (INSERT).
        public function ajoutProduit(){
        $px=$this->request->getVar('prix');
        $designation=$this->request->getVar('designation');
    //-----> validation et divers contrôles omis <------
        $produit=new Panier();
        $produit->designation=$designation;
        $produit->prix=$px;
        $produit->save();
    }

    public function commandes($id){
        echo 'Commandes du client '.$id.'<br/>';
        $client=Client::find($id);
        $commandes=$client->commandes()->get();
        foreach ($commandes as $commande) {
        echo "Num commande: ".$commande->id." Etat: ".$commande->etat."<br/>";
        }
        }
    
}