<template>
    <div>
        <v-app>
            <v-main>
                <template>
                    <div class="my-5">
                        <v-row justify="end">
                            <v-dialog
                                v-model="dialog"
                                persistent
                                max-width="290">
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn 
                                        class="mx-4 mt-2" 
                                        depressed
                                        color="#FABB5C"
                                        dark
                                        v-bind="attrs"
                                        v-on="on"
                                    >
                                        Nueva categoria
                                    </v-btn>
                                </template>
                                <v-card>
                                    <v-card-title class="text-h5">
                                        <v-text-field
                                            v-model="category_name"
                                            label="Regular"
                                        ></v-text-field>
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
                                            @click="saveCategorie"
                                        >
                                            Aceptar
                                        </v-btn>
                                    </v-card-actions>
                                </v-card>
                            </v-dialog>
                        </v-row>
                    </div>
                    <div class="row position-relative d-flex justify-content-center align-items-center">
                        <div v-for="data in categories" :key="data.id">
                            <div class="col-12">
                                <v-card
                                    max-width="200"
                                    class="pb-5"
                                    style="overflow: visible; margin: 4em !important;"
                                    @click="showProduct(data.id)"
                                >
                                    <template slot="progress">
                                        <v-progress-linear
                                            color="deep-purple"
                                            height="10"
                                            indeterminate
                                        ></v-progress-linear>
                                    </template>
                                    <div class="position-relative d-flex justify-content-center align-items-center m-2"> 
                                        <div>
                                            <v-img
                                                height="150"
                                                width="150"
                                                src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
                                                style="border-radius: 50%; transform: translateY(-25%);"
                                            ></v-img>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-center">
                                        <v-card-title>{{ data.category_name }}</v-card-title>
                                    </div>
                                </v-card>
                            </div>
                        </div>
                    </div>
                </template>
            </v-main>
        </v-app>
    </div>
</template>

<script>
    
    export default {
        data(){
            return {
                categories: [],
                dialog: false,
                category_name: '',
            }
        },

        created(){
            this.initialize();
        },

        methods:{
            initialize(){
                axios.get('/getCategorie').then(res => {
                    console.log("respuesta");
                    console.log(res.data);
                    this.categories = res.data.categories;
                }).catch(error => {
                    console.log(error.response);
                });
            },

            showProduct(categorie_id){

                console.log(categorie_id);
            },

            saveCategorie(){
                console.log(this.category_name);
                let category_name = {
                    'category_name': this.category_name
                };

                axios.post('/newCategorie', category_name).then(res => {
                    console.log('Respuesta de registro');
                    console.log(res.data);
                    this.initialize();
                    this.close();
                }).catch(error => {
                    console.log(error.response);
                })
            },

            close(){
                this.category_name = '';
                this.dialog = false
            },
        },
    }

</script>
