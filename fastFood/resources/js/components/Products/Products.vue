<template>
    <div>
        <v-app>
            <v-main>
                <Button @click="$parent.$parent.close()">volver</Button>
                <template>
                    <v-data-table
                        :headers="headers"
                        :items="desserts"
                        :search="search"
                        sort-by="calories"
                        class="elevation-1"
                    >
                        <template v-slot:top>
                        <v-toolbar
                            flat
                        >
                            <v-toolbar-title><v-text-field
                                v-model="search"
                                append-icon="mdi-magnify"
                                label="Buscar"
                                single-line
                                hide-details
                            ></v-text-field></v-toolbar-title>
                            <v-divider
                                class="mx-4"
                                inset
                                vertical
                            ></v-divider>
                            <v-spacer></v-spacer>
                            <v-dialog
                                v-model="dialog"
                                    max-width="500px"
                                >
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn
                                    color="primary"
                                    dark
                                    class="mb-2"
                                    v-bind="attrs"
                                    v-on="on"
                                    >
                                        Nuevo producto
                                    </v-btn>
                                </template>
                                <v-card>
                                    <v-card-title>
                                        <span class="text-h5">{{ formTitle }}</span>
                                    </v-card-title>

                                    <v-card-text>
                                        <v-container>
                                            <v-row>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                    <v-text-field
                                                    v-model="editedItem.product_name"
                                                    label="Nombre"
                                                    ></v-text-field>
                                                </v-col>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                    <v-text-field
                                                    v-model="editedItem.description"
                                                    label="Descripción"
                                                    ></v-text-field>
                                                </v-col>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                    <v-text-field
                                                    v-model="editedItem.price"
                                                    label="Precio"
                                                    ></v-text-field>
                                                </v-col>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                    <v-text-field
                                                    v-model="editedItem.quantity"
                                                    label="Cantidad"
                                                    ></v-text-field>
                                                </v-col>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                    <v-select
                                                    label="Categoría"
                                                    :items="categories"
                                                    :key="editedItem.category_id"
                                                    v-model="editedItem.category_name"></v-select>
                                                </v-col>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                <v-select
                                                    label="Estado"
                                                    :items="status"
                                                    v-model="editedItem.status"></v-select>
                                                </v-col>
                                                <v-col
                                                    cols="12"
                                                    sm="6"
                                                    md="4"
                                                >
                                                <v-file-input truncate-length="50" label="File input" v-model="selectedFile" @change="captureFileName" required></v-file-input>
                                                </v-col>
                                            </v-row>
                                        </v-container>
                                    </v-card-text>

                                    <v-card-actions>
                                    <v-spacer></v-spacer>
                                    <v-btn
                                        color="blue darken-1"
                                        text
                                        @click="close"
                                    >
                                        Cancelar
                                    </v-btn>
                                    <v-btn
                                        color="blue darken-1"
                                        text
                                        @click="save"
                                    >
                                        Guardar
                                    </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-toolbar>
                        </template>
                        <template v-slot:item.actions="{ item }"> 
                            <v-btn
                                x-small
                                class="mx-1"
                                fab
                                dark
                                color="primary"
                                @click="editItem(item)"
                            >
                                <v-icon dark>
                                    mdi-pencil
                                </v-icon>
                            </v-btn>
                        </template>
                        <template v-slot:no-data>
                        <v-btn
                            color="primary"
                            @click="initialize"
                        >
                            Reset
                        </v-btn>
                        </template>
                    </v-data-table>
                </template>
            </v-main>
        </v-app>
    </div>
</template>
<script>
export default{
    props: ['dataCategory'],
    data(){
        return  {
            status: ['Activo','Inactivo'],
            search: '',
            id: '',
            selectedFile: null,
            categories: [],
            dialog: false,
            dialogDelete: false,
            showProduct: false,
            headers: [
                { text: 'Categoria', value: 'product_name' },
                { text: 'Categoria', value: 'description' },
                { text: 'Categoria', value: 'price' },
                { text: 'Estado', value: 'status' },
                { text: '', value: 'actions', sortable: false },
            ],
            desserts: [],
            editedIndex: -1,
            editedItem: {
                id: '',
                product_name: '',
                description: '',
                price: 0,
                quantity: 0,
                status: '',
                category_id: '',
                image_url: '',
            },
            items: [
                'Activo',
                'Inactivo',
            ],
        };
    },
    computed: {
        formTitle () {
            return this.editedIndex === -1 ? 'Nueva categoria' : 'Editar categoria'
        },
    },

    watch: {
        dialog (val) {
            val || this.close()
        },
    },

    created(){
        this.id = this.dataCategory.id;
        this.initialize();
    },

    methods: {
        initialize(){
            axios.get(`/consultProduct/${this.id}`).then(respuesta => {
                console.log("Respuesta del servidor");
                console.log(respuesta.data);
                this.desserts = respuesta.data.product;
            }).catch(error => {
                console.log("Error en servidor");
                console.log(error);
                swal("Error", "CLick", "danger");
                console.log(error.response);
            });
        },

        captureFileName() {
            if (this.selectedFile && this.selectedFile.name) {
                this.editedItem.image_url = this.selectedFile.name;
            }
        },

        getCategories() {
            axios.get('/ExtractCategories').then(
                response => {response.data.categories.forEach(category => {this.categories.push(category.category_name);
            });}).catch((error) => {
                console.error('Error al obtener la lista de categorías: ', error);
                console.log(error.response);
            });
        },

        editItem (item) {
            this.editedIndex = this.desserts.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
        },

        close () {
            this.showProduct = false;
            this.dialog = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, this.defaultItem)
                this.editedIndex = -1
            })
        },

        save() { 
            console.log(this.editedItem.category_id)
            if(this.formTitle === 'Registro de producto'){
                if (!this.editedItem.product_name || !this.editedItem.description || !this.editedItem.quantity || !this.editedItem.price || !this.editedItem.image_url || !this.editedItem.status || !this.editedItem.category_name) {
                        swal({
                            title: "Campos Vacíos",
                            text: "Por favor complete todos los campos",
                            icon: "error",
                            button: "Aceptar",
                        });
                        return;
                    }

                    axios.post('/registerProduct', this.editedItem).then(respuesta => {
                        if (respuesta.data.status) {
                            console.log("Registro exitoso");
                            console.log(respuesta.data)
                            swal({
                                title: "Registro Exitoso",
                                text: "El producto se registró correctamente",
                                icon: "success",
                                button: "Aceptar",
                            });
                            this.editedItem.product_name = null;
                            this.editedItem.description = null;
                            this.editedItem.quantity = null;
                            this.editedItem.price = null;
                            this.editedItem.image_url = null;
                            this.editedItem.status = null;
                            this.editedItem.category_name = null;
                            this.editedItem.category_id = null; // Restablecer category_id
                            this.list();
                            this.close();
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
                }else{
                    axios.put(`/UpdateProduct/${this.editedItem.id}`, this.editedItem).then(respuesta => {
                        if (respuesta.data.status) {
                            console.log("Actualización exitosa");
                            swal({
                                title: "Actualizacion Exitoso",
                                text: "El producto se actualizo correctamente",
                                icon: "success",
                                button: "Aceptar",
                            });
                            this.list();
                            this.close();
                        } else {
                            console.log("Error: Los datos están duplicados");
                            swal({
                                title: "Error",
                                text: "Error verifica que todo este bien",
                                icon: "danger",
                                button: "Aceptar",
                            });
                        }
                    }).catch(error => {
                        swal({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Algo Salio mal! (Existe)',
                        });
                        console.log("Error en servidor");
                        console.log(error);
                        console.log(error.response);
                    });
            }
        },

        alert(title, text, type){
            swal({
                title: title,
                text: text,
                icon: type,
                button: "Aceptar",
            });
        },
    },

}
</script>