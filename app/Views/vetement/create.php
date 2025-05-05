<nav>
    <a href="http://localhost/~baptiste.vincent/PPE/public/vetement" class="nav-item">Vêtements</a>


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
<br>

<?= \Config\Services::validation()->listErrors() ?>
<form action='create' method='post'>
    <?= csrf_field() ?>
    <label for="typeVetement">Type de vêtement</label>
    <input class="donnee" type="input" name="typeVetement" /><br />

    <label for="modele">Modèle</label>
    <input class="donnee" type="input" name="modele" /><br />

    <label for="couleur">Couleur</label>
    <input class="donnee" type="input" name="couleur" /><br />

    <label for="taille">Taille</label>
    <input class="donnee" type="input" name="taille" /><br />

    <label for="prix">Prix</label>
    <input class="donnee" type="input" name="prix" /><br />
    
    <br>
    <input class="ajouter" type="submit" name="submit" value="Créer vêtement" />
</form>