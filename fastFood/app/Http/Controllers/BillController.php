<?php

namespace App\Http\Controllers;
use App\Models\Bill;

use Illuminate\Http\Request;

class BillController extends Controller
{   

    public function billsView(){
        return view('Bills/bills');
    }


    public function getBills(){
        $bills = Bill::get();

        $data = [
            'status' => true,
            'bills' => $bills,
        ];

        return response()->json($data);
    }
}
