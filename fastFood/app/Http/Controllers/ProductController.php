<?php

namespace App\Http\Controllers;
use App\Models\Product;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;

class ProductController extends Controller
{
    public function viewConsult(){
        return view('Products/product');
    }
    public function ShowProductsIndivual($id)
    {
        // Realizar la consulta SQL para obtener los productos de la categoría específica
        $consultproducts = DB::table('products')
                        ->where('products.category_id', $id)
                        ->get();

        $data = [
            'status' => true,
            'product' => $consultproducts,
        ];
        return response()->json($data);
    }
    public function ShowProducts()
    {
        $showproducts = Product::get();
        $data = [
            'status' => true,
            'showproducts' => $showproducts,
        ];
        return response()->json($data);
    }
}
