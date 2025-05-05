<nav>
    <a href="http://localhost/~baptiste.vincent/PPE/public/vetement" class="nav-item">Vêtements</a>
    

    <?php if (session()->get('email') != 'admin@gmail.com'){ ?>
    <a href="http://localhost/~baptiste.vincent/PPE/public/panier" class="nav-item">Panier</a>
    <?php } ?>

    <?php if (session()->get('idUtilisateur') != null) : ?>
    <a href="http://localhost/~baptiste.vincent/PPE/public/deconnexion" onclick="return confirm('Voulez-vous vraiment vous deconnecter ?');" class="nav-item">Se déconnecter</a>
    <?php else : ?>

    <a href="http://localhost/~baptiste.vincent/PPE/public/connexion" class="nav-item">Se connecter</a>
    <?php endif; ?>

    <a href="http://localhost/~baptiste.vincent/PPE/public/inscription" class="nav-item">Inscription</a>
    <?php if (session()->get('email') == 'admin@gmail.com'){ ?>
        <a href="http://localhost/~baptiste.vincent/PPE/public/vetement/create" class="nav-item">Creer un nouveau vêtement</a>
    <?php } ?>
</nav>


<?php foreach ($vetement as $vetement): ?>

<div class="main">
<h2><?php echo $vetement->idVetement; ?></h3>
<u>Type de vêtement</u> : 
<?php echo $vetement->typeVetement; ?>
<br>
<u>Modèle</u> : 
<?php echo $vetement->modele; ?>
<br>
<u>Couleur</u> : 
<?php echo $vetement->couleur; ?>
<br>
<u>Taille</u> : 
<?php echo $vetement->taille; ?>
<br>
<u>Prix</u> : 
<?php echo $vetement->prix; ?> €
<br> <br>


<?php if (session()->get('email') == 'admin@gmail.com'){ ?>
<form action="<?php echo base_url('vetement/delete/'.$vetement->idVetement);?>" method="POST">
<input class="supprimer" type="submit" onclick="return confirm('Voulez-vous vraiment supprimer cet élément ?');" value="Supprimer" />
</form>
<?php } ?>

</div>
<br>
<?php endforeach; ?>