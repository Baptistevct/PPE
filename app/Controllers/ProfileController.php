<?php
namespace App\Controllers;
use CodeIgniter\Controller;
class ProfileController extends Controller
{
public function index()
{
$session = session();
echo "Hello : ".$session->get('nom');

?> 
<link rel="stylesheet" type="text/css" href="<?php echo base_url().'style.css'; ?>"/>
<br>
<nav>
    <a href="http://localhost/~baptiste.vincent/PPE/public/vetement" class="nav-item">Vêtements</a>

    <?php if (session()->get('email') != 'admin@gmail.com'){ ?>
    <a href="http://localhost/~baptiste.vincent/PPE/public/panier" class="nav-item">Panier</a>
    <?php } ?>

    <?php if (session()->get('email') == 'admin@gmail.com'){ ?>
        <a href="http://localhost/~baptiste.vincent/PPE/public/vetement/create" class="nav-item">Creer un nouveau vêtement</a>
    <?php } ?>
</nav>


<?php }
}