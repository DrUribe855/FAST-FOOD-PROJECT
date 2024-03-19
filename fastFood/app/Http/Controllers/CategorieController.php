<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Categorie;

class CategorieController extends Controller
{
    public function index(){
        return view('categorie.categorie');
    }

    public function getCategorie(){

        $data = [
            'status' => true,
            'categories' => Categorie::get(),
        ];

        return response()->json($data);
    }

    public function intertCategorie(Request $request){
        $categorie = new Categorie($request->all());
        $categorie->save();

        $data = [
            'status' => true,
            'categorie' => $categorie,
        ];

        return response()->json($request);
    }
}
