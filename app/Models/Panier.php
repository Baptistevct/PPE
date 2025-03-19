<?php
namespace App\Models;
use Illuminate\Database\Eloquent\Model;

class Panier extends Model
{
    protected $table = 'vetements';
    protected $primaryKey = 'idVetement';
    public $timestamps=false ;
}

