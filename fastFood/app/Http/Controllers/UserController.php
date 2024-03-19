<?php

namespace App\Http\Controllers;
use App\Models\User;
use Illuminate\Http\Request;

class UserController extends Controller
{
    public function index(){
        return view('login');
    }
    public function RegisterUser(Request $request)
    {
        $users = new User();
        $users->document = $request->input('document');
        $users->name = $request->input('name');
        $users->phone_number = $request->input('phone_number');
        $users->email = $request->input('email');
        $users->status = $request->input('status');
        $users->password = $request->input('password');
        $users->save();

        $data = [
            'status' => true,
            'users' => $users
        ];
        return response()->json($data);
    }
    
}
