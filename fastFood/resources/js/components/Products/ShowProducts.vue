<template>
    <div >
        <button v-if="!formRegister" type="submit" class="btn btn-warning" @click="formRegister=true">Registrar Producto</button>
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
                        <button class="btn custom-btn same-width-btn">Modificar</button>
                    </div>
                    <div>
                        <button class="btn custom-btn same-width-btn">Desactivar</button>
                    </div>
                </div>

            </div>

        </section>
        <formulario v-if="formRegister"></formulario>

    </div>
</template>

<script>
import RegisterProduct from "./RegisterProduct.vue";
export default {
    components: {
        'formulario': RegisterProduct,
        // 'editar': Editar,
    },
    data() {
        return {
            listProducts: [],
            formRegister: false,
        };
    },
        created() {
            this.list();
        },
    methods: {
        backComponent() {
            this.formRegister = false;
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