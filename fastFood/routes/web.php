<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\LoginController;


use App\Http\Controllers\ProductController;

Route::get('/', [UserController::class, 'index']);
Route::post('/login', [LoginController::class, 'verifyUserData'])->name('login');

//Ver Formulario de Registro de Usuario
Route::get('/userRegistration', [UserController::class, 'showRegister'])->name('userRegistration');

// Register User --Monsalve 
Route::post("/RegisterUser", [UserController::class, "registerUser"])->name("user.register");

//productos
Route::get('/consultProduct/{id}', [ProductController::class, 'ShowProductsIndivual'])->name('consultProduct');
Route::get('/consult', [ProductController::class, 'viewConsult'])->name('view');
Route::get('/viewProducts', [ProductController::class, 'viewProducts'])->name('viewP');
Route::get('/showProducts', [ProductController::class, 'ShowProducts'])->name('showproducts');
Route::post('/registerProduct', [ProductController::class, 'registerProduct'])->name('registerProduct');
Route::put("/UpdateProduct/{id}", [ProductController::class, 'updateProducto'])->name('updateProd');
Route::get('/ExtractCategories', [ProductController::class, 'getCategorys']);

