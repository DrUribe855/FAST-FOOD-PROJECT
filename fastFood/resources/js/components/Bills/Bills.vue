<template>
    <div>
        <v-app>
            <v-main>
                <v-card>
                    <v-card-title>
                    <v-text-field
                        v-model="search"
                        append-icon="mdi-magnify"
                        label="Search"
                        single-line
                        hide-details
                    ></v-text-field>
                    </v-card-title>
                    <v-data-table
                    :headers="headers"
                    :items="desserts"
                    :search="search"
                    ></v-data-table>
                </v-card>
            </v-main>
        </v-app>
    </div>
</template>
<script>
  export default {
    data () {
      return {
        search: '',
        headers: [
          { text: 'Total', value: 'total' },
          { text: 'Estado', value: 'status' },
          { text: 'Cliente', value: 'user_id' },
          { text: 'Dirección', value: 'address_id' },
          { text: 'Fecha de compra', value: 'date' },
        ],
        desserts: [],
      }
    },
    created(){
        this.getBills();
    },
    methods:{
        getBills(){
            axios.get('/getBills').then(resp => {
                this.desserts = resp.data.bills;
                console.log("Respuesta del servidor - Lista de ventas");
                console.log(this.desserts);
            }).catch(error => {
                console.log("Error en consulta GET de ventas");
                console.log(error.response);
            })
        }
    },
  }
</script>