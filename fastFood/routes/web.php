<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\CategorieController;

Route::get('/', [UserController::class, 'index']);

// Categorie
Route::get('/categorie', [CategorieController::class, 'index']);
Route::get('/getCategorie', [CategorieController::class, 'getCategorie']);
Route::post('/newCategorie', [CategorieController::class, 'intertCategorie']);
