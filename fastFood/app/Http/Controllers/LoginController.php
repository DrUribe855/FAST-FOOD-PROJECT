<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
use App\Http\Requests\LoginDataRequest;

class LoginController extends Controller
{
    public function verifyUserData(LoginDataRequest $request){

        
        $credentials = $request->only('email', 'password');

        /* Se hace busqueda de usuario mediante email */

        
        $user = User::where('email', $credentials["email"])->first();

        /* Se verifica la existencia del usuario */

        if(!$user){
            return $this->sendErrorResponse('Usuario no encontrado');
        }

        /* Se verifica el estado del usuario */

        if($user->status !== 'Activo'){
            return $this->sendErrorResponse('Cuenta desactivada');
        }

        /* Se intenta autenticar el usuario */
            
        if(Auth::attempt([$credentials])){
            return response()->json([
                'status' => true,
                'message' => "Datos correctos",
            ]);
        }else{
            return $this->sendErrorResponse("Datos incorrectos");
        }
    }


    public function logout(Request $request){
        Auth::logout(); // Destruye la sesión del usuario

        $request->session()->invalidate(); // Invalida la sesión actual
        return redirect('/');
    }

    /* Enviar respuesta de error */
    protected function sendErrorResponse($message){
        return response()->json([
            'status' => false,
            'message' => $message,
        ]);
    }

}
