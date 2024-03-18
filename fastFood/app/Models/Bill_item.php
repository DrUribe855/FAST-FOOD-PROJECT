<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Bill_item extends Model
{
    use HasFactory;

    protected $fillable = [
        'quantity',
        'subtotal'
    ];

    public function bill() : HasMany{
        return $this->hasMany(Bill::class);
    }

    public function product() : BelongsTo{
        return $this->belongsTo(Product::class);
    }
}
