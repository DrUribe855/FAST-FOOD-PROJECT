<template>
    <div>
        
        <section class="bg-white p-3 rounded shadow mb-3 mt-3" v-for="listProduct in listProducts" :key="listProduct.id">
            <div class="row align-items-center">

                <div class="col-md-3">
                    <div class="image">
                        <img class="img-" :src="listProduct.image_url" alt="">
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

    </div>
</template>


<script>
export default {
    data() {
        return {
            listProducts: [],
        };
    },
        created() {
            this.list();
        },
    methods: {
        
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