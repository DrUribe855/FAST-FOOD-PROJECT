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
Route::get('/categorie', [CategoryController::class, 'index']);// ->middleware('can: admin.generalShow');
Route::get('/getCategorie', [CategoryController::class, 'getCategorie']);// ->middleware('can: admin.generalShow');
Route::post('/newCategorie', [CategoryController::class, 'insertCategorie']);// ->middleware('can: admin.insert');
Route::post('/editCategorie', [CategoryController::class, 'editCategorie']);// ->middleware('can: admin.update');
Route::post('/searchCategorie', [CategoryController::class, 'searchCategorie']);// ->middleware('can: admin.generalShow');


//Ver Formulario de Registro de Usuario
Route::get('/userRegistration', [UserController::class, 'showRegister'])->name('userRegistration');

// Register User --Monsalve 
Route::post("/RegisterUser", [UserController::class, "registerUser"])->name("user.register");

//productos
Route::get('/consultProduct/{id}', [ProductController::class, 'ShowProductsIndivual'])->name('consultProduct');// ->middleware('can: admin.generalShow');
Route::get('/consult', [ProductController::class, 'viewConsult'])->name('view');// ->middleware('can: admin.generalShow');
Route::get('/viewProducts', [ProductController::class, 'viewProducts'])->name('viewP');// ->middleware('can: admin.generalShow');
Route::get('/showProducts', [ProductController::class, 'ShowProducts'])->name('showproducts');// ->middleware('can: admin.generalShow');
Route::post('/registerProduct', [ProductController::class, 'registerProduct'])->name('registerProduct');// ->middleware('can: admin.generalShow');
Route::put("/UpdateProduct/{id}", [ProductController::class, 'updateProducto'])->name('updateProd');// ->middleware('can: admin.generalShow');
Route::get('/ExtractCategories', [ProductController::class, 'getCategorys']);
Route::get('/consultCategory/{id}', [ProductController::class, 'consultIndivualCategory']);// ->middleware('can: admin.generalShow');


// Registro de ventas
Route::get('/sellsView', [BillController::class, 'billsView']);// ->middleware('can: admin.generalShow');
Route::get('/getBills', [BillController::class, 'getBills']);// ->middleware('can: admin.generalShow');



