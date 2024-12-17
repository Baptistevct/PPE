<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
rel="stylesheet">
<title>S’inscrire sur notre site</title>
</head>
<body>
<div class="container mt-5">
<div class="row justify-content-md-center">
<div class="col-5">
<h2>S’inscrire</h2>
<?php if(isset($validation)):?>
<div class="alert alert-warning">
<?= $validation->listErrors() ?>
</div>
<?php endif;?>
<form action="<?php echo base_url(); ?> traiteInscription" method="POST">
<div class="form-group mb-3">
<input type="text" name="nom" placeholder="Nom" value="<?=
set_value('nom') ?>" class="form-control" >
</div>
<div class="form-group mb-3">
<input type="text" name="prenom" placeholder="Prenom" value="<?=
set_value('prenom') ?>" class="form-control" >
</div>
<div class="form-group mb-3">
<input type="email" name="email" placeholder="Email"
value="<?= set_value('email') ?>" class="form-control" >
</div>
<div class="form-group mb-3">
<input type="password" name="mdp"
placeholder="Password" class="form-control" >
</div>
<div class="form-group mb-3">
<input type="password" name="confirmpassword"
placeholder="Confirm Password" class="form-control" >
</div>
<div class="d-grid">
<button type="submit" class="btn
btn-dark">Inscription</button>
</div>
</form>
</div>
</div>
</div>
</body>
</html>