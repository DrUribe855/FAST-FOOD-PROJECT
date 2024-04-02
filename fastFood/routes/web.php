<?php

use App\Http\Controllers\CategoryController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\LoginController;
use App\Http\Controllers\BillController;


use App\Http\Controllers\ProductController;

Route::get('/', [UserController::class, 'index']);
Route::post('/login', [LoginController::class, 'verifyUserData'])->name('login');
Route::get('/logout', [LoginController::class, 'logout']);

// Categories
Route::get('/', [UserController::class, 'index']);
Route::get('/categorie', [CategoryController::class, 'index']);
Route::get('/getCategorie', [CategoryController::class, 'getCategorie']);
Route::post('/newCategorie', [CategoryController::class, 'insertCategorie']);
Route::post('/editCategorie', [CategoryController::class, 'editCategorie']);
Route::post('/deleteCategorie', [CategoryController::class, 'deleteCategorie']);
Route::post('/searchCategorie', [CategoryController::class, 'searchCategorie']);


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
Route::get('/consultCategory', [ProductController::class, 'consultIndivualCategory']);


// Registro de ventas
Route::get('/sellsView', [BillController::class, 'billsView']);
Route::get('/getBills', [BillController::class, 'getBills']);



