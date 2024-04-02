<?php

namespace App\Http\Controllers;
use App\Models\Product;
use App\Models\Category;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Validator;
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
    
    public function viewProducts(){
        return view('Products/showProducts');
    }

    public function ShowProducts()
    {


        $showProducts = Product::whereHas('category')
                        ->with('category')
                        ->get();
        $data = [
            'status' => true,
            'showproducts' => $showProducts,
        ];
        return response()->json($data);
    }

    public function getCategorys()
    {
        $categories = Category::select('category_name')->get();
        $data = [
            'status'=> true,
            'categories'=> $categories
            ];
        return response()->json($data);
    }

    public function registerProduct(Request $request)
    {
        // // Definir reglas de validación
        // $rules = [
        //     'product_name' => 'required|string',
        //     'description' => 'required|string',
        //     'quantity' => 'required|int',
        //     'price' => 'required|int',
        //     'image_url' => 'required|string',
        //     'status' => 'required|string',
        //     'category_id' => 'required|int',
        // ];

        // // Aplicar validación
        // $validator = Validator::make($request->all(), $rules);

        // // Verificar si la validación falla
        // if ($validator->fails()) {
        //     echo("Error en el tipo de dato");
        //     return response()->json(['status' => false, 'error' => $validator->errors()], 400);
        // }

        

        $category = Category::where('category_name', $request->category_name)->first();

        $product = new Product();
        $product->product_name = $request->product_name;
        $product->description = $request->description;
        $product->quantity = $request->quantity;
        $product->price = $request->price;
        $product->image_url = $request->image_url;
        $product->status = $request->status;
        $product->category_id = $category->id;
        $product->save();

        $data = [
            'status' => true,
            'product' => $product
        ];

        return response()->json($data);

        
    }
    public function updateProducto(Request $request, $id)
    {

        // // Definir reglas de validación
        // $rules = [
        //     'product_name' => 'required|string',
        //     'description' => 'required|string',
        //     'quantity' => 'required|int',
        //     'price' => 'required|int',
        //     'image_url' => 'required|string',
        //     'status' => 'required|string',
        //     'category_id' => 'required|int',
        // ];

        // // Aplicar validación
        // $validator = Validator::make($request->all(), $rules);

        // // Verificar si la validación falla
        // if ($validator->fails()) {
        //     echo("Error en el tipo de dato");
        //     return response()->json(['status' => false, 'error' => $validator->errors()], 400);
        // }

        $product = Product::find($id);
        $product->product_name = $request->input('product_name');
        $product->description = $request->input('description');
        $product->quantity = $request->input('quantity');
        $product->price = $request->input('price');
        $product->image_url = $request->input('image_url');
        $product->status = $request->input('status');
        $product->category_id = $request->input('category_id');
        $product->save();
        
        $data = [
            'status' => true,
            'product' => $product,
            'id' => $id,
            'request' => $request,
        ];
        return response()->json($data);
    }
}
