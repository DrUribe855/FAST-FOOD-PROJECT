<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use \App\Models\User;
use Spatie\Permission\Models\Role;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {   
        $this->call(RoleSeeder::class);
        $users = User::factory(30)->create();
        User::factory(10)->create();

        $adminRole = Role::where('name','Admin')->first();
        $users[0]->assignRole($adminRole);
    }
}
