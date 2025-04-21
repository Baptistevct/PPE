<?php
namespace App\Controllers;
use CodeIgniter\Controller;
use CodeIgniter\API\ResponseTrait;

use App\Models\Vetement;
use App\Models\Categorie;

class WebServiceController extends BaseController {
    
    use ResponseTrait;
    function getCategories(){
        $typeVetements = Vetement::select('typeVetement')
            ->distinct()
            ->get();
        return $this->respond($typeVetements);
    }


    /**
     * retourne un tableau de tailles pour un modele donné
     */
    private function tableauDeTailles($modele){
        $tableauTailles=array();

        $db = \Config\Database::connect();
        $sql="SELECT taille FROM vetements WHERE modele = ?;";
        $req=$db->query($sql, [$modele]);
        $results = $req->getResult();
        foreach($results as $ligne){
            $taille=$ligne->taille;
            //ajoute la taille parcourue à la fin du tableau
            $tableauTailles[]=$taille;
        }
        return $tableauTailles;
    }


    function getVetementParCategoriesId(String $catId){
        $vetements = Vetement::where('modele', $catId)
            ->select('modele', 'typeVetement')
            ->distinct()
            ->get();
        
        foreach($vetements as $vetement) {
            $tailles = Vetement::where('modele', $vetement->modele)
                ->pluck('taille')
                ->toArray();
            $vetement->tailles = $tailles;
        }
        
        return $this->respond($vetements);
    }
}