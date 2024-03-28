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
            'category_name' => 'required|alpha',
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
            'category_name' => 'required|alpha',
        ]);

        $update = Category::find($request->input('id'));
        $update->category_name = $request->input('category_name');
        $update->save();

        $data = [
            'status' => true,
            'update' => $update,
        ];

        return response()->json($data);
    }

    public function deleteCategorie(Request $request){
        $category = Category::find($request->input('id'));
        $category->delete();

        $data = [
            'status' => true,
        ];

        return response()->json($request);
    }

    public function searchCategorie(Request $request){
        $data = [
            'status' => true,
            'categories' => Category::where('category_name', $request->input('name'))->get(),
        ];

        return response()->json($data);
    }
}
