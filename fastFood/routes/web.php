<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\UserController;
use App\Http\Controllers\LoginController;

Route::get('/', [UserController::class, 'index']);

Route::post('/login', [LoginController::class, 'verifyUserData'])->name('login');

