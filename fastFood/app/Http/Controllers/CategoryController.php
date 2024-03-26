<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Models\Category;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class CategoryController extends Controller
{
    public function showRegisterCategory(){
        return view('Categories/registerCategory');
    }

    public function registerCategory(Request $request){
        
        $rules = [
            'category_name' => 'required|string|unique:categories',
        ];

        // Aplicar validación
        $validator = Validator::make($request->all(), $rules);

        // Verificar si la validación falla
        if ($validator->fails()) {
            echo("Error en el tipo de dato");
            return response()->json(['status' => false, 'error' => $validator->errors()], 400);
        }

        $categories = new Category();
        $categories->category_name = $request->input('category_name');
        $categories->save();
        
        $data = [
            'status' => true,
            'categories' => $categories
        ];
        return response()->json($data);

    }
}
