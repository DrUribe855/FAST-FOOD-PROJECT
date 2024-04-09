<template>
    <div>
        <v-app>
            <v-main v-if="!showProduct">
                <div class="mt-6" style="width: 700px;margin: 0 auto; display: block;">
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
                                    Nueva categoria
                                    </v-btn>
                                </template>
                                <v-card>
                                    <v-card-title>
                                    <span class="text-h5">{{ formTitle }}</span>
                                    </v-card-title>

                                    <v-card-text>
                                    <v-container>
                                        <v-row>
                                            <v-col>
                                                <v-text-field
                                                v-model="editedItem.category_name"
                                                label="Nombre de categoria"
                                                ></v-text-field>
                                            </v-col>
                                            <v-col>
                                                <v-select
                                                    v-if="formTitle == 'Editar categoria'"
                                                    v-model="editedItem.status"
                                                    :items="items"
                                                    label="Estado"
                                                ></v-select>  
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
                                <v-btn
                                    x-small
                                    class="mx-1"
                                    fab
                                    dark
                                    color="red"
                                    @click="openWindowProduct(item)"
                                >
                                    <v-icon dark>
                                        mdi-format-list-bulleted-square
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
                </div>
            </v-main>
            <product v-if="showProduct" :dataCategory="category"></product>
        </v-app>
    </div>
</template>

<script>
    import Product from '../Products/ShowProducts.vue'
    export default {
        components: {
            'product': Product,
        },
        data: () => ({
            search: '',
            dialog: false,
            dialogDelete: false,
            showProduct: false,
            headers: [
                { text: 'Categoria', value: 'category_name' },
                { text: 'Estado', value: 'status' },
                { text: '', value: 'actions', sortable: false },
            ],
            desserts: [],
            editedIndex: -1,
            editedItem: {
                category_name: '',
                status: '',
            },
            defaultItem: {
                Categoria: '',
            },
            items: [
                'Activo',
                'Inactivo',
            ],
            category: [],
        }),

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

        created () {
            this.initialize()
        },

        methods: {
            initialize(){
                    axios.get('/getCategorie').then(res => {
                        this.desserts = res.data.categories;
                    }).catch(error => {
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

            save () {
                if (this.editedIndex > -1) {
                    console.log("12 ",this.editedItem);
                    axios.post('/editCategorie', this.editedItem).then(res =>{
                        this.initialize();
                        this.alert('OK', 'La categoria se modifico correctamente', 'success')
                    }).catch(error => {
                        this.alert('ERROR', `Error no identificado codigo de error: ${error.response.status}`, 'error');
                    });
                } else {
                    axios.post('/newCategorie', this.editedItem).then(res => {
                        console.log('Respuesta de registro');
                        console.log(res.data);
                        this.initialize();
                        this.alert('OK', 'La categoria se registro correctamente', 'success')
                    }).catch(error => {
                        this.alert('ERROR', `Error no identificado codigo de error: ${error.response.status}`, 'error');
                    })
                }
                this.close()
            },

            alert(title, text, type){
                swal({
                    title: title,
                    text: text,
                    icon: type,
                    button: "Aceptar",
                });
            },

            openWindowProduct(item){
                this.category = item;
                this.showProduct = true;
            },
        },
    }
</script>
