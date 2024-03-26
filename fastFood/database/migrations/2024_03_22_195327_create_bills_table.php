<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('bills', function (Blueprint $table) {
            $table->id();
            $table->float('total');
            $table->date('date');
            $table->enum('status', ['Activo', 'Inactivo']);
            $table->unsignedBigInteger('bill_item_id');
            $table->unsignedBigInteger('user_id');
            $table->unsignedBigInteger('address_id');
            $table->timestamps();

            $table->foreign('bill_item_id')->references('id')->on('bill_items');
            $table->foreign('user_id')->references('id')->on('users');
            $table->foreign('address_id')->references('id')->on('addresses');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('bills');
    }
};
