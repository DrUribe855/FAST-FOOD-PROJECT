<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Product extends Model
{
    use HasFactory;

    protected $fillable = [
        'product_name',
        'description',
        'quantity',
        'price',
        'image_url',
        'status'
    ];

    public function bill_item() : HasMany{
        return $this->hasMany(Bill_item::class);
    }

    public function category() : BelongsTo{
        return $this->belongsTo(Category::class);
    }
}
