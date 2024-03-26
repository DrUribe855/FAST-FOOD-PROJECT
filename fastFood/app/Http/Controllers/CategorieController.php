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
        $request->validate([
            'category_name' => 'required|alpha',
        ]);
        
        $categorie = new Categorie($request->all());
        $categorie->save();

        $data = [
            'status' => true,
            'categorie' => $categorie,
        ];

        return response()->json($request);
    }

    public function editCategorie(Request $request){
        $request->validate([
            'category_name' => 'required|alpha',
        ]);

        $update = Categorie::find($request->input('id'));
        $update->category_name = $request->input('category_name');
        $update->save();

        $data = [
            'status' => true,
            'update' => $update,
        ];

        return response()->json($data);
    }
}
