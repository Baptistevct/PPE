<?php
namespace App\Controllers;
use CodeIgniter\Controller;
use CodeIgniter\API\ResponseTrait;

use App\Models\Vetement;
use App\Models\Categorie;

class WebServiceController extends BaseController {
    
    use ResponseTrait;
    function getCategories(){
        $categories= Categorie::all();
        return $this->respond($categories);
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



    function getVetementParCategoriesId(int $catId){
        $chaine="[";
        $db = \Config\Database::connect();
        $req=$db->query("SELECT DISTINCT modele FROM vetements");
        $results = $req->getResult();
        $results_sz=count($results);
        $com=0;
        
        foreach($results as $ligne){
            $mod=$ligne->modele;

            //nom des modeles
            $chaine = $chaine.'{ "modele" : "'.$mod.'","tailles": ';

            //recupere les tailles pour ce modele
            $chaineTableau=json_encode($this->tableauDeTailles($mod));
            $chaine=$chaine.$chaineTableau;
            $chaine=$chaine.'}';
            $com++;
            if($com<$results_sz){
                $chaine.=",";
            }
        }
        $chaine.="]";
        echo $chaine;
    }

}