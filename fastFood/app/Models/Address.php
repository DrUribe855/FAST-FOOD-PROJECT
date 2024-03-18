<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Address extends Model
{
    use HasFactory;

    protected $fillable = [
        'neighborhood',
        'address',
        'floor_number'
    ];

    public function bill() : HasMany{
        return $this->hasMany(Bill::class);
    }

    public function user() : BelongsTo{
        return $this->belongsTo(User::class);
    } 
}
