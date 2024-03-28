<template>
    <div>
        <v-app>
            <v-main>
                <template v-if="!showProduct">
                    <div class="my-5">
                        <v-row justify="end">
                            <v-text-field
                                class="mx-4"
                                v-model="searchText"
                                label="Buscar"
                            ></v-text-field>
                            <v-btn 
                                class="mr-4 mt-2" 
                                depressed
                                color="#E48700"
                                dark
                                @click="search">
                                Buscar
                            </v-btn>
                            <v-btn 
                                class="mr-4 mt-2" 
                                depressed
                                color="#FABB5C"
                                dark
                                @click="openWindow('','')">
                                Nueva categoria
                            </v-btn>
                            <v-dialog
                                v-model="dialog"
                                persistent
                                max-width="290">
                                <v-card>
                                    <v-card-title class="text-h5">
                                        <v-text-field
                                            v-model="category_name"
                                            label="Nombre categoria"
                                            outlined
                                        ></v-text-field>
                                        <v-select
                                            v-model="status"
                                            :items="items"
                                            label="Estado"
                                            outlined
                                        ></v-select>
                                    </v-card-title>
                                    <v-card-actions>
                                        <v-spacer></v-spacer>
                                        <v-btn
                                            color="black"
                                            text
                                            @click="close"
                                        >
                                            Cancelar
                                        </v-btn>
                                        <v-btn
                                            color="#E48700"
                                            text
                                            @click="typeSave"
                                        >
                                            Aceptar
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-row>
                    </div>
                    <div  class="row position-relative d-flex justify-content-center align-items-center">
                        <div v-for="data in categories" :key="data.id">
                            <div>
                                <v-card
                                    height="250"
                                    width="250"
                                    class="pb-5"
                                    style="overflow: visible; margin: 4em !important;">
                                    <div class="position-relative d-flex justify-content-center align-items-center mx-2 my-0 p-0" @click="redirectProduct(data.id)"> 
                                        <div>
                                            <v-img
                                                height="150"
                                                width="150"
                                                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                                                style="border-radius: 50%; transform: translateY(-25%);"
                                            ></v-img>
                                        </div>
                                    </div>
                                    <div class="row d-flex justify-content-center mx-5">
                                        <v-card-title>{{ data.category_name }}</v-card-title>
                                        <v-btn
                                            class="mx-1"
                                            color="#FABB5C"
                                            fab
                                            small
                                            dark
                                            @click="openWindow(data.category_name, data.id, data.status)">
                                            <v-icon>mdi-pencil</v-icon>
                                        </v-btn>
                                    </div>
                                </v-card>
                            </div>
                        </div>
                    </div>
                </template>
                <product v-if="showProduct" :categorie_id="categorie_id"></product>
            </v-main>
        </v-app>
    </div>
</template>

<script>
    import Product from "../Products/Products.vue";
    import swal from 'sweetalert';

    export default {
        components: {
            'product': Product,
        },

        data(){
            return {
                categories: [],
                dialog: false,
                dialogEdit: false,
                showProduct: false,
                showBtn: false,
                categorie_id: '',
                category_name: '',
                status: '',
                categorieIdEdit: '',
                searchText: '',
                items: [
                    'ACTIVO',
                    'INACTIVO',
                ],
            }
        },

        created(){
            this.initialize();
        },

        methods:{
            initialize(){
                axios.get('/getCategorie').then(res => {
                    this.categories = res.data.categories;
                }).catch(error => {
                    console.log(error.response);
                });
            },

            typeSave(){
                console.log(this.showBtn);
                if (!this.showBtn) {
                    this.saveCategorie();
                }else{
                    this.saveCategorieEdit();
                }
            },
            
            redirectProduct(categorie_id){
                this.showProduct = true;
                this.categorie_id = categorie_id;
                console.log(this.categorie_id);
            },

            saveCategorie(){
                if (this.category_name != '') {
                    let category_name = {
                        'category_name': this.category_name
                    };
    
                    axios.post('/newCategorie', category_name).then(res => {
                        console.log('Respuesta de registro');
                        console.log(res.data);
                        this.initialize();
                        this.close();
                        this.alert('OK', 'La categoria se registro correctamente', 'success')
                    }).catch(error => {
                        console.log(error.response);
                        if (error.response.status == 422) {
                            this.alert('ERROR', 'El nombre solo pueden ser letras', 'error')
                        }else{
                            this.alert('ERROR', 'Error no identificado', 'error')
                        }
                    })
                }else{
                    this.alert('ERROR', 'El nombre de la categoria no puede esta vacio', 'error')
                }
            },

            close(){
                this.category_name = '';
                this.dialog = false;
            },

            openWindow(name, id, status){
                if (name != '') {
                    this.showBtn = true;
                }else{
                    this.showBtn = false;
                }
                this.categorieIdEdit = id;
                this.category_name = name;
                this.status = status;
                this.dialog = true;
            },

            saveCategorieEdit(){
                let data = {
                    'id': this.categorieIdEdit,
                    'category_name': this.category_name,
                    'status': this.status,
                }
                axios.post('/editCategorie', data).then(res =>{
                    this.initialize();
                    this.close();
                    this.alert('OK', 'La categoria se modifico correctamente', 'success')
                }).catch(error => {
                    if (error.response.status == 422) {
                        this.alert('ERROR', 'El nombre solo pueden ser letras', 'error')
                    }else{
                        this.alert('ERROR', 'Error no identificado', 'error')
                    }
                    console.log(error.response);
                });
            },

            alert(title, text, type){
                swal({
                    title: title,
                    text: text,
                    icon: type,
                    button: "Aceptar",
                });
            },
            search(){
                let data = {
                    'name': this.searchText
                };
                axios.post('/searchCategorie', data).then(res => {
                    this.categories = res.data.categories;
                    this.searchText = '';
                }).catch(error => {
                    console.log(error);
                });
            }
        },
    }

</script>
