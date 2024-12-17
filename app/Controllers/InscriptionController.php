<?php
namespace App\Controllers;
use App\Models\Users;
class InscriptionController extends BaseController
{
public function index()
{
helper(['form']);
$data = [];
echo view('inscription', $data);
}
public function traiteInscription()
{
    helper(['form']);
    $rules = [
    'nom'    => 'required|min_length[2]|max_length[50]',
    'prenom' => 'required|min_length[2]|max_length[50]',
    'email'  => 'required|min_length[4]|max_length[100]|valid_email|is_unique[users.email]',
    'mdp'    => 'required|min_length[4]|max_length[20]',
    'confirmpassword' => 'matches[mdp]'];

    if($this->validate($rules)){
    $usr= new Users();
    $usr->nom = $this->request->getVar('nom');
    $usr->prenom = $this->request->getVar('prenom');
    $usr-> email = $this->request->getVar('email');
    $usr ->mdp = password_hash($this->request->getVar('mdp'), PASSWORD_DEFAULT) ;
    $usr->save();
    return redirect()->to('/connexion');

    }else{
        $data['validation'] = $this->validator;
    echo view('inscription', $data);
    }


}

}