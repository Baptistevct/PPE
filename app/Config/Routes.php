<?php

use CodeIgniter\Router\RouteCollection;

/**
 * @var RouteCollection $routes
 */
$routes->get('/', 'Home::index');
$routes->get('/vetement', 'Vetements::index');

$routes->get('/vetement/create', 'Vetements::create');
$routes->post('/vetement/create', 'Vetements::create');

$routes->get('/vetement/delete/(:num)', 'Vetements::delete/$1');
$routes->post('/vetement/delete/(:num)', 'Vetements::delete/$1');

$routes->get('/panier', 'PanierController::index');
$routes->post('/ajouterPanier', 'PanierController::ajouterPanier');

$routes->get('/', 'ConnexionController::index');
$routes->get('/inscription', 'InscriptionController::index');
$routes->post('/traiteInscription', 'InscriptionController::traiteInscription');
$routes->get('/connexion', 'ConnexionController::index');
$routes->post('/traiteConnexion', 'ConnexionController::traiteConnexion');
$routes->get('/profile', 'ProfileController::index',['filter' => 'authGuard']);

$routes->get('/deconnexion', 'DeconnexionController::deconnexion');
$routes->post('/deconnexion', 'DeconnexionController::deconnexion');

$routes->get('/api/getCategories', 'WebServiceController::getCategories');
$routes->get('/api/getVetementParCategoriesId/(:num)', 'WebServiceController::getVetementParCategoriesId/$1');




