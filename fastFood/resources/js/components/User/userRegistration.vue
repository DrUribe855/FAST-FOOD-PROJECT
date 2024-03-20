<template>
    <div>
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="bg-light p-4 rounded-card shadow" style="width: 450px;">
            
                <div class="row mt-2">
                    <div class="col-12">
                        <h3 class="text-center text-uppercase mb-4"><b> Crea tu cuenta</b></h3>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Documento</p>
                        <input type="number" class="form-control" v-model="registerUser.document">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class=" col-12">
                        <p class="poppins-label">Nombre</p>
                        <input type="text" class="form-control" v-model="registerUser.name">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Telefono</p>
                        <input type="number" class="form-control" v-model="registerUser.phone_number">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Email</p>
                        <input type="email" class="form-control" v-model="registerUser.email">
                    </div>
                </div>

                <div class="row mb-4">
                    <div class="col-12">
                        <p class="poppins-label">Contraseña</p>
                        <input type="password" class="form-control" v-model="registerUser.password">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <button class="btn form-control custom-btn " @click="save()">Continuar</button>
                    </div>
                </div>

            </div>
        </div>
    </div>
    
</template>
<script>
    import swal from 'sweetalert';
    export default {
        data: () => ({
        valid: false,
            registerUser: {
                id:'',
                document: '',
                name: '',
                phone_number: '',
                email: '',
                status: 'Activo',
            },
        }),
        methods:{
            save() { 
                // Validar campos vacíos
                if (!this.registerUser.document || !this.registerUser.name || !this.registerUser.phone_number || !this.registerUser.email || !this.registerUser.password) {
                    swal({
                        title: "Campos Vacíos",
                        text: "Por favor complete todos los campos",
                        icon: "error",
                        button: "Aceptar",
                    });
                    return; // Detener la ejecución del método si hay campos vacíos
                }

                // Si todos los campos están completos, enviar la solicitud al servidor
                axios.post('/RegisterUser', this.registerUser)
                .then(respuesta => {
                    if (respuesta.data.status) {
                        console.log("Registro exitoso");
                        swal({
                            title: "Registro Exitoso",
                            text: "El usuario se registró correctamente",
                            icon: "success",
                            button: "Aceptar",
                        });
                        //Limpiar los campos de entrada
                        this.registerUser.document = null;
                        this.registerUser.name = null;
                        this.registerUser.phone_number = null;
                        this.registerUser.email = null;
                        this.registerUser.password = null;
                        //this.desserts = respuesta.data.machineryData;
                    } else {
                        console.log("Error:");
                        swal({
                            title: "Registro Fallido",
                            text: "El usuario no fue registrado correctamente",
                            icon: "error",
                            button: "Aceptar",
                        });
                    }
                }).catch(error => {
                    if (error.response.status == 422) {
                        alert("Existe");
                    }
                    console.log("Error en servidor");
                    console.log(error);
                    console.log(error.response);
                });
            },
        },

    }
</script>
