<template>
    <div class="container ">
        <div>
            <table class="table mb-3">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">NAME</th>
                        <th scope="col">DESCRIPTION</th>
                        <th scope="col">QUANTITY</th>
                        <th scope="col">PRICE</th>
                        <th scope="col">IMG</th>
                        <th scope="col">STATUS</th>
                        <th scope="col">CATEGORY</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="listProduct in listProducts">
                        <th scope="row">{{ listProduct.id }}</th>
                        <td>{{ listProduct.product_name }}</td>
                        <td>{{ listProduct.description }}</td>
                        <td>{{ listProduct.quantity }}</td>
                        <td>{{ listProduct.price }}</td>
                        <td>{{ listProduct.inage_url }}</td>
                        <td>{{ listProduct.status }}</td>
                        <td>{{ listProduct.category_id }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
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