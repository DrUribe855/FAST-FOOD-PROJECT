<template>
    <div>
        <v-app>
            <v-main>
                <template>
                    <v-form v-model="valid">
                        <v-container>
                            <v-row>
                                <v-col cols="12" md="4">
                                    <v-text-field
                                        v-model="registerUser.document"
                                        :rules="[v => !!v || 'Document is required']"
                                        :counter="10"
                                        label="Documento"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-text-field
                                        v-model="registerUser.name"
                                        :rules="[v => !!v || 'Name is required']"
                                        label="Nombre"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-text-field
                                        v-model="registerUser.phone_number"
                                        :rules="[v => !!v || 'Phone is required']"
                                        :counter="10"
                                        label="Telefono - Celular"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-text-field
                                        v-model="registerUser.email"
                                        :rules="[v => !!v || 'Email is required']"
                                        label="Email"
                                        required
                                    ></v-text-field>
                                </v-col>
                                <v-col cols="12" md="4" >
                                    <v-text-field
                                        v-model="registerUser.password"
                                        :rules="[v => !!v || 'Password is required']"
                                        label="Contraseña"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-btn  depressed color="primary" @click="save()">
                                    Enviar
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-form>
                </template>
            </v-main>
        </v-app>
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
                // Limpiar los campos de entrada
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