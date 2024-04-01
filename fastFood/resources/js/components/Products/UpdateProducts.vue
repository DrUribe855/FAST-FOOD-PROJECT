<template>
    <div>
        <v-app>
            <v-main>
                <v-container class="mx-auto mt-2 pa-5">
                    <v-card-title class="justify-center">
                        MODIFICAR PRODUCTO
                    </v-card-title>
                    <v-container>
                        <v-row>
                            <v-col cols="12" md="6">
                                <v-text-field v-model="datosEdit.product_name" label="Nombre" required></v-text-field>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field v-model="datosEdit.description" label="Descripción" required></v-text-field>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field v-model="datosEdit.quantity" label="Cantidad" required></v-text-field>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field v-model="datosEdit.price" label="Precio" required></v-text-field>
                            </v-col>
                            <v-col cols="12">
                                <v-file-input truncate-length="50" label="File input" v-model="selectedFile" @change="captureFileName" required></v-file-input>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-text-field v-model="datosEdit.status" label="Estado" required></v-text-field>
                            </v-col>
                            <v-col cols="12" md="6">
                                <v-select v-model="datosEdit.category_id" :items="categories" label="Categoría" item-text="category_name" item-value="id"></v-select>
                            </v-col>
                        </v-row>
                    </v-container>
                    <v-card-actions class="d-flex justify-center">
                        <v-btn @click="UpdateProduct()" elevation="2" color="grey" dark class="primary-btn">Actualizar</v-btn>
                    </v-card-actions>
                </v-container>
            </v-main>
        </v-app>
    </div>
</template>
<script>
export default {
    props:['ProductData'], 
    data(){
        return{
            //crear arreglo
            valid: false,
            datosEdit: {},
            categories: [],
            selectedFile: null,
        };
    },
    created() {
        this.getCategorias();
        this.datosEdit = this.ProductData;
        console.log("Estoy en update", this.ProductData);
        // console.log("Datos del categoria", this.categories);
    },
    methods: {
        UpdateCorrect(){
            this.$parent.backModificarProduct();
            this.$parent.list();
        },
        showProducts(){
            this.$parent.list();
        },
        captureFileName() {
            // Verifica si se ha seleccionado un archivo
            if (this.selectedFile && this.selectedFile.name) {
                this.datosEdit.image_url = this.selectedFile.name; // Almacena solo el nombre del archivo
            }
        },
        UpdateProduct() {
            console.log("Datos enviados al modificar", this.datosEdit);
            axios.put(`/UpdateProduct/${this.datosEdit.id}`, this.datosEdit).then(respuesta => {
                if (respuesta.data.status) {
                    console.log("Actualización exitosa");
                    swal({
                        title: "Actualizacion Exitoso",
                        text: "El producto se actualizo correctamente",
                        icon: "success",
                        button: "Aceptar",
                    });
                    this.$parent.backModificarProduct();
                    this.showProducts();
                } else {
                    console.log("Error: Los datos están duplicados");
                    swal({
                        title: "Error",
                        text: "Error verifica que todo este bien",
                        icon: "danger",
                        button: "Aceptar",
                    });
                }
            })
            .catch(error => {
                swal({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Algo Salio mal! (Existe)',
                });
                console.log("Error en servidor");
                console.log(error);
                console.log(error.response);
            });
        },
        // buttonBack(){
        //     this.$parent.backComponent();
        //     this.$parent.list();
        // },
        // showProducts(){
        //     this.$parent.list();
        // },
        getCategorias() {
            // console.log("Datos del proveedor", this.categories);
            axios.get('/ExtractCategories').then(respuesta => {
                this.categories = respuesta.data.categories;
            }).catch((error) => {
                console.error('Error al obtener la lista de categorias: ', error);
            });
        },
    },
}
</script>
  