<template>
    <div class="d-flex justify-content-center">
        <div class="container d-flex justify-content-center align-items-center" style="height: 20em; width: 30em;">
            <div class="p-4 rounded-card shadow" style="width: 50em; background-color: white ;">

                <div class="row mt-2">
                    <div class="col-12">
                        <h3 class="text-center text-uppercase mb-4 poppins-label">Crea tu cuenta</h3>
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-6">
                        <p class="poppins-label">Documento</p>
                        <input type="number" class="form-control" v-model="registerUser.document" placeholder="Ingrese el documento">
                    </div>
                
                    <div class=" col-6">
                        <p class="poppins-label">Nombre</p>
                        <input type="text" class="form-control" v-model="registerUser.name" placeholder="Ingrese su nombre">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-6">
                        <p class="poppins-label">Telefono</p>
                        <input type="number" class="form-control" v-model="registerUser.phone_number" placeholder="Ingrese su telefono">
                    </div>
            
                    <div class="col-6">
                        <p class="poppins-label">Email</p>
                        <input type="email" class="form-control" v-model="registerUser.email" placeholder="Ingrese su email">
                    </div>
                </div>

                <div class="row mb-2">
                    <div class="col-12">
                        <p class="poppins-label">Contraseña</p>
                        <input type="password" class="form-control" v-model="registerUser.password" placeholder="Ingrese una contraseña">
                    </div>
                </div>
                <div class="row mb-2">
                    <div class="col-12">
                        <button class="btn form-control custom-btn poppins-label" @click="save()">Continuar</button>
                    </div>
                </div>
                <div class="small-text">
                  <b>¿Ya tienes una cuenta? <a class="btn-inicio"@click="$parent.backToLogin()">Inicia sesión</a></b>
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
                        // Retrasar la redirección por 3 segundos
                        setTimeout(() => {
                            // Redireccionar a la ruta especificada
                            window.location.href = '/'; // Redirige a la raíz de tu aplicación
                        }, 2000);
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
