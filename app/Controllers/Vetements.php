<?php
namespace App\Controllers;
use App\Models\Vetement;

class Vetements extends BaseController {
    public function index()
    {
        $data['vetement'] = Vetement::all();
        $data['title']= "Vêtements";

        echo view('vetement/templates/header', $data);
        echo view('vetement/index', $data);
        echo view('vetement/templates/footer');
    }


    public function create()
    {

        if ($this->request->getMethod() === 'POST'){
            //on traite les données postées
            if($this->validate([
                'typeVetement' => 'required|min_length[3]|max_length[50]',
                'modele' => 'permit_empty|min_length[3]|max_length[50]',
                'couleur' => 'required|min_length[3]|max_length[50]',
                'taille' => 'required|min_length[1]|max_length[7]',
                'prix' => 'required|min_length[1]|max_length[3]|decimal',
                ])) {
                    //la validation a réussi
                    $vetement=new Vetement();
                    $vetement->typeVetement=$this->request->getPost('typeVetement');
                    $vetement->modele=$this->request->getPost('modele');
                    $vetement->couleur=$this->request->getPost('couleur');
                    $vetement->taille=$this->request->getPost('taille');
                    $vetement->prix=$this->request->getPost('prix');
                    $vetement->save();
                    echo view('vetement/success');
                }else{
                    //la validation a échoué
                    echo view('vetement/templates/header',['title' => 'Saisir un vêtement']);
                    //on transmet les erreurs de validation
                    $data['validation'] = $this->validator;
                    echo view('vetement/create', $data);
                    echo view('vetement/templates/footer');
                }
        } else {
        //Nous affichons le form de creation
        echo view('vetement/templates/header',['title' => 'Saisir un vêtement']);
        echo view('vetement/create');
        echo view('vetement/templates/footer');
        }
    }

    public function delete($id){
        $model = new Vetement();
        $model->where('idVetement', $id)->delete();
        return redirect()->to('/vetement');
    }
}