<template>
    <div>
        <v-app>
            <v-main>
                <v-form >
                    <v-container>
                        <v-row>
                            <v-col cols="12" md="4" >
                                <v-text-field
                                v-model="datosEdit.product_name"
                                    label="Nombre"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12" md="4" >
                                <v-text-field
                                    v-model="datosEdit.description"
                                    label="descripcion"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12" md="4" >
                                <v-text-field
                                    v-model="datosEdit.quantity"
                                    label="quantity"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12" md="4" >
                                <v-text-field
                                    v-model="datosEdit.price"
                                    label="price"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12" md="4" >
                                <v-text-field
                                    v-model="datosEdit.status"
                                    label="Status"
                                    required
                                ></v-text-field>
                            </v-col>
                            <v-col cols="12" md="4" >
                                <v-file-input
                                    truncate-length="15"
                                    v-model="selectedFile"
                                    @change="captureFileName"
                                ></v-file-input>
                            </v-col>
                            <v-col class="d-flex" cols="12" sm="6" >
                                <v-select
                                    v-model="datosEdit.category_id"
                                    :items="categories"
                                    label="Categoria"
                                    item-text="category_name"
                                    item-value="id"
                                    required
                                ></v-select>
                            </v-col>
                        </v-row>
                    </v-container>
                    <v-btn
                        elevation="2"
                        @click="UpdateProduct()"
                    >Actualizar</v-btn>
                </v-form>
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
  