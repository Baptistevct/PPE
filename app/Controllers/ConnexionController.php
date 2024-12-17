<?php
namespace App\Controllers;
use App\Models\Users;
class ConnexionController extends BaseController
{

public function index()
{
helper(['form']);
echo view('connexion');
}

public function traiteConnexion()
{
    $session = session();

    $email = $this->request->getVar('email');
    $mdp = $this->request->getVar('mdp');
    $usr = Users::where('email', $email)->first();
    if($usr)
    {
        $pass = $usr->mdp;
        $authenticatePassword = password_verify($mdp, $pass);
        if($authenticatePassword)
        {
            $ses_data = [
            'idUtilisateur' => $usr->idUtilisateur,
            'nom' => $usr->nom,
            'prenom' => $usr->prenom,
            'email' => $usr->email,
            'isLoggedIn' => TRUE
            ];
            $session->set($ses_data);
            return redirect()->to('/profile');
        }
        else
        {
            $session->setFlashdata('msg', 'Password is incorrect.');
            return redirect()->to('/connexion');
        }
    }
    else
    {
    $session->setFlashdata('msg', 'Email does not exist.');
    return redirect()->to('/connexion');
    }
}
}