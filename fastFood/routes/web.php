<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;

Route::get('/', [UserController::class, 'index']);
Route::post("/RegisterUser", [UserController::class, "RegisterUser"])->name("user.register");
