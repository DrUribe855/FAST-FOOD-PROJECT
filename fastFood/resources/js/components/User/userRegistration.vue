<template>
    <div class="container d-flex justify-content-center align-items-center vh-100">
        <div class="bg-light p-4 rounded shadow" style="width: 600px;">
            <form>

                <div class="row mb-2">
                    <div class="col-12">
                        <h3 class="text-center text-uppercase mb-4"><b> Crea tu cuenta</b></h3>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Documento</p>
                        <input type="number" class="form-control" v-model="document">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class=" col-12">
                        <p class="poppins-label">Nombre</p>
                        <input type="text" class="form-control" v-model="name">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Telefono</p>
                        <input type="number" class="form-control" v-model="phone_number">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Email</p>
                        <input type="email" class="form-control" v-model="email">
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-12">
                        <p class="poppins-label">Contraseña</p>
                        <input type="password" class="form-control" v-model="password">
                    </div>
                </div>

                <div class="row">
                    <div class="col-12">
                        <button class="btn form-control custom-btn" type="submit">Continuar</button>
                    </div>
                </div>

    
            </form>
        </div>
    </div>
</template>