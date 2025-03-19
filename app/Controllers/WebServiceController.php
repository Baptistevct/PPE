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

    function getVetementParCategoriesId(int $catId){
        $vetements = Vetement::where('idCategories','=', $catId)->get();
        return $this->respond($vetements);
    }

}