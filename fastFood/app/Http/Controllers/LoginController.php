<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Auth;

class LoginController extends Controller
{
    public function verifyUserData(Request $request){

        
        $credentials = $request->validate([
            'email' => 'email',
            'password' => 'required', 
        ]);

        if($this->fieldValidation($credentials)){
            $user = User::where('email', $credentials["email"])->get();
            if(!$user){
                return response()->json([
                    'status' => false,
                    'message' => 'No registrado'
                ], 401); // Unauthorized 
                
            }
            
            if(Auth::attempt($credentials)){
                return response()->json([
                    'status' => true,
                    'message' => 'Datos correctos'
                ], 200); // OK
            }else{
                return response()->json([
                    'status' => false,
                    'message' => 'Datos incorrectos',
                    'datos' => $credentials,
                    'datos2' => $user,
                ], 401); // Unauthorized 
            }
        }else{

            return response()->json([
                'status' => false,
                'message' => 'Datos incompletos'
            ], 400); // Not found
        }

        


    }

    public function fieldValidation($data){

        if($data["email"] == '' || $data["password"] == ''){
            return false;
        }

        return true;
    }

}
