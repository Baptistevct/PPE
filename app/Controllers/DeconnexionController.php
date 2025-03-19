<?php
namespace App\Controllers;
use App\Models\Users;
class DeconnexionController extends BaseController
{
    public function deconnexion()
    {
        if (session()->get('idUtilisateur') != null){
        session_destroy();
        return redirect()->to('http://localhost/~baptiste.vincent/PPE/public/vetement');
        }
    }

}