<?php
namespace App\Models;
use Illuminate\Database\Eloquent\Model;

class Vetement extends Model{
    protected $table = 'vetements';
    protected $primaryKey = 'idVetement';
    public $timestamps = false;

    protected $fillable = [
        'typeVetement',
        'modele',
        'couleur',
        'taille',
        'prix',
        'idUtilisateur'
    ];

    public function user() {
        return $this->belongsTo(Users::class, 'idUtilisateur', 'idUtilisateur');
    }
}