<template>
    <div>
        <v-app>
            <v-main>
                <v-row justify="center">
                    <v-btn color="primary" dark @click.stop="openModal = true">Open Dialog</v-btn>

                    <v-dialog v-model="openModal" max-width="290">
                        <v-card>
                            <v-card-title class="text-h7 mb-3">
                                REGISTRAR CATEGORIA
                            </v-card-title>

                            <v-card-text>
                                <v-row class="mb-3">
                                    <v-col cols="12">
                                        <v-text-field label="Nombre Categoria" outlined v-model="name_category"></v-text-field>
                                    </v-col>
                                </v-row>
                            </v-card-text>

                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn color="primary" text @click="openModal = false">Volver</v-btn>
                                <v-btn color="primary" text @click="sendData()" >Aceptar</v-btn>
                            </v-card-actions>

                        </v-card>
                    </v-dialog>
                </v-row>

            </v-main>
        </v-app>
    </div>
</template>
  
<script>

    import swal from 'sweetalert';

    export default {
        data: () => ({
        
            openModal: false,
            name_category: '',
            
        }),
        methods: {
            sendData(){
                this.openModal = false;
                //Validar Campo Vacio
                if(!this.name_category){
                    showAlert('Campos Vacios', 'Debe de diligenciar todos los campos', 'warning');
                    return;
                }

                //Si no hay campos vacios, enviar solicitud
                axios.post('/registerCategory', { category_name: this.name_category})
                .then(response => {
                    console.log(response);
                    if(response.data.status){
                        console.log("Registro Exitoso");
                        showAlert('Categoria Registrada', 'La categoria fue registrada Exitosamente', 'success');
                    }else{
                        console.log("Registro Fallido");
                        showAlert('Registro Fallido', "La categoria no se pudo registrar", "error");
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
            showAlert(title, text, icon){
                swal({
                    title: title,
                    text: text,
                    icon: icon,
                    button: 'Aceptar',
                });
            
            
            }
        }
        

    }
</script>