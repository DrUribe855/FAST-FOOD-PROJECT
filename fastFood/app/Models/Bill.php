<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\Relations\HasMany;
use Illuminate\Database\Eloquent\Relations\BelongsTo;

class Bill extends Model
{
    use HasFactory;

    protected $fillable = [
        'total',
        'date',
        'status'
    ];

    public function payment() : HasMany{
        return $this->hasMany(Payment::class);
    }

    public function user() : BelongsTo{
        return $this->belongsTo(User::class);
    } 

    public function bill_item() : BelongsTo{
        return $this->belongsTo(Bill_item::class);
    }

    public function address() : BelongsTo{
        return $this->belongsTo(Address::class);
    }
}
