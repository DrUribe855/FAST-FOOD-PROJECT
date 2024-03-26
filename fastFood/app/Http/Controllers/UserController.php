<?php

namespace App\Http\Controllers;
use App\Models\User;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;

class UserController extends Controller
{
    public function index(){
        return view('Login/login');
    }

    public function showRegister(){
        return view('User/userRegistration');
    }

    public function registerUser(Request $request)
    {
        // Definir reglas de validación
        $rules = [
            'document' => 'required|int|unique:users',
            'name' => 'required|string',
            'phone_number' => 'required|int|unique:users',
            'email' => 'required|email|unique:users',
            'status' => 'required|string',
            'password' => 'required|string',
        ];

        // Aplicar validación
        $validator = Validator::make($request->all(), $rules);

        // Verificar si la validación falla
        if ($validator->fails()) {
            echo("Error en el tipo de dato");
            return response()->json(['status' => false, 'error' => $validator->errors()], 400);
        }

        $users = new User();
        $users->document = $request->input('document');
        $users->name = $request->input('name');
        $users->phone_number = $request->input('phone_number');
        $users->email = $request->input('email');
        $users->status = $request->input('status');
        $users->password = bcrypt ($request->input('password'));
        $users->save();

        $data = [
            'status' => true,
            'users' => $users
        ];
        return response()->json($data);
    }
    

    
}
