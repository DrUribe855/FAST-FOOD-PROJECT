<template>
    <div>
        <div v-if="updateP">
            <button v-if="!formRegister" type="submit" class="btn btn-warning" @click="formRegister=true">Registrar Producto</button>
            <div>
                <section v-if="!formRegister" class="bg-white p-3 rounded shadow mb-3 mt-3" v-for="listProduct in listProducts" :key="listProduct.id">
                    <div class="row align-items-center">

                        <div class="col-md-3">
                            
                            <div class="image">
                                <img :src="listProduct.image_url" alt="Imagen">
                            </div>

                        </div>

                        <div class="col-md-3 ">
                            <div class="content">
                                <h4 class="raleway-font"><b>{{ listProduct.product_name }}</b></h4>
                                <p class="inter-font">{{ listProduct.description }}</p> 
                            </div>
                        </div>

                        <div class="col-md-3">
                            
                        </div>

                        <div class="col-md-3 text-center">
                            <div class="mb-2">
                                <button class="btn custom-btn same-width-btn" @click="viewUpdate(listProduct)">Modificar</button>
                            </div>
                            <div>
                                <button class="btn custom-btn same-width-btn">Desactivar</button>
                            </div>
                        </div>
                    </div>
                
                    <div class="col-md-3 ">
                        <div class="content">
                            <h4 class="raleway-font"><b>{{ listProduct.product_name }}</b></h4>
                            <p class="inter-font">{{ listProduct.description }}</p>
                        </div>
                    </div>

                    <div class="col-md-3">
                        
                    </div>

                    <div class="col-md-3 text-center">
                        <div class="mb-2">
                            <button class="btn custom-btn same-width-btn">Modificar</button>
                        </div>
                        <div>
                            <button class="btn custom-btn same-width-btn">Desactivar</button>
                        </div>
                    </div>

                </section>
            </div>
        </div>
        <formulario v-if="formRegister"></formulario>
        <update v-if="!updateP" :ProductData="dataUpdate"></update>
    </div>
</template>

<script>
import RegisterProduct from "./RegisterProduct.vue";
import UpdateProducts from "./UpdateProducts.vue";
export default {
    components: {
        'formulario': RegisterProduct,
        'update': UpdateProducts,
    },
    data() {
        return {
            listProducts: [],
            categories: [],
            formRegister: false,
            updateP: true,
            dataUpdate: {},
        };
    },
    created() {
        this.list();
    },
    methods: {
        viewUpdate(ProductData) {
            console.log("Estoy entrando", ProductData);
            this.dataUpdate = ProductData
            this.updateP = false;
        },
        backComponent() {
            this.formRegister = false;
        },
        backModificarProduct(){
            this.formRegister = false;
            this.updateP = true;
        },
        list() {
            console.log("Entre")
            axios.get('/showProducts').then(respuesta => {
                console.log("Respuesta del servidor");
                console.log(respuesta.data);
                this.listProducts = respuesta.data.showproducts;
            }).catch(error => {
                console.log("Error en servidor");
                console.log(error);
                console.log(error.response);
            });
        },
    },
};
</script>