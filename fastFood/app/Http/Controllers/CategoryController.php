<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Category;

class CategoryController extends Controller
{
    public function index(){
        return view('Category.category');
    }

    public function getCategorie(){
        $data = [
            'status' => true,
            'categories' => Category::get(),
        ];

        return response()->json($data);
    }

    public function insertCategorie(Request $request){
        $request->validate([
            'category_name' => 'required',
        ]);
        
        $categorie = new Category($request->all());
        $categorie->save();

        $data = [
            'status' => true,
            'categorie' => $categorie,
        ];

        return response()->json($request);
    }

    public function editCategorie(Request $request){
        $request->validate([
            'category_name' => 'required',
        ]);

        $update = Category::find($request->input('id'));
        $update->category_name = $request->input('category_name');
        $update->status = $request->input('status');
        $update->save();

        $data = [
            'status' => true,
            'update' => $update,
        ];

        return response()->json($data);
    }
}
