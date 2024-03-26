<template>
    <div>
        <v-app>
            <v-main>
                <template>

                </template>
            </v-main>
        </v-app>
    </div>
</template>
<script>
export default{
    props: ['categorie_id'],
    data(){
        return  {
            data: [],
            valid: false,
            id: '',
        };
    },

    created(){
        this.id = this.categorie_id;
        this.initialize();
    },

    methods: {
        initialize(){
            axios.get(`/consultProduct/${this.id}`).then(respuesta => {
                console.log("Respuesta del servidor");
                console.log(respuesta.data);
                this.data = respuesta.data;
            }).catch(error => {
                console.log("Error en servidor");
                console.log(error);
                swal("Error", "CLick", "danger");
                console.log(error.response);
            });
        },
    },
}
</script>