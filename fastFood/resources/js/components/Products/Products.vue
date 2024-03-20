<template>
    <div>
        <v-app>
            <v-main>
                <template>
                    <v-form v-model="valid">
                        <v-container>
                            <v-row>
                                <!-- Este es el id que recibe de la categoria -->
                                <v-col cols="12" md="4">
                                    <v-text-field
                                        v-model="id"
                                        :rules="[v => !!v || 'id is required']"
                                        :counter="10"
                                        label="id"
                                        required
                                    ></v-text-field>
                                </v-col>

                                <v-col cols="12" md="4" >
                                    <v-btn  depressed color="primary" @click="UpdateUser()">
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
export default{
    
    data(){
        return  {
            valid: false,
            id: '',
        };
    },
    methods: {
        UpdateUser(){
            console.log("datos enviados al modificar",this.id);
            axios.get(`/consultProduct/${this.id}`).then(respuesta => {
                console.log("Respuesta del servidor");
                console.log(respuesta.data);
                swal("Good job!", "You clicked the button!", "success");    
                // this.$parent.listUser();
                // this.$parent.volverFormulario();
            }).catch(error => {
                console.log("Error en servidor");
                console.log(error);
                swal("Error", "CLick", "danger");
                console.log(error.response);
            });
        },
    },
}
</script>