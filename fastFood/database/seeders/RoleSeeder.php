<?php

namespace Database\Seeders;

use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Spatie\Permission\Models\Role;
use Spatie\Permission\Models\Permission;

class RoleSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $roleAdmin = Role::create(['name' => 'Admin']);
        $roleUser = Role::create(['name' => 'User']);
        
        // Permisos para el admin
        Permission::create(['name' => 'admin.generalShow'])->assignRole($roleAdmin);
        Permission::create(['name' => 'admin.insert'])->assignRole($roleAdmin);
        Permission::create(['name' => 'admin.update'])->assignRole($roleAdmin);
        Permission::create(['name' => 'admin.delete'])->assignRole($roleAdmin);
    }
}
