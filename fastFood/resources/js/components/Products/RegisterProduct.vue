<template>
  <div>
    <v-app>
      <v-main>
        <v-container class="mx-auto mt-2 pa-5" max-width="800">
          <v-card-title class="justify-center">
              REGISTRO DE PRODUCTO
          </v-card-title>
          <v-container>
            <v-row>
              <v-col cols="12" md="6">
                <v-text-field v-model="registerProduct.product_name" label="Nombre" required></v-text-field>
              </v-col>
              <v-col cols="12" md="6">
                <v-text-field v-model="registerProduct.description" label="Descripción" required></v-text-field>
              </v-col>
              <v-col cols="12" md="6">
                <v-text-field v-model="registerProduct.quantity" label="Cantidad" required></v-text-field>
              </v-col>
              <v-col cols="12" md="6">
                <v-text-field v-model="registerProduct.price" label="Precio" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-file-input truncate-length="50" label="File input" v-model="selectedFile" @change="captureFileName" required></v-file-input>
              </v-col>
              <v-col cols="12" md="6">
                <v-text-field v-model="registerProduct.status" label="Estado" required></v-text-field>
              </v-col>
              <v-col cols="12" md="6">
                <v-select v-model="registerProduct.category_id" :items="categories" label="Categoría" item-text="category_name" item-value="id"></v-select>
              </v-col>
            </v-row>
          </v-container>
          <v-card-actions class="d-flex justify-center">
            <v-btn @click="buttonBack()" elevation="2" color="grey" class="mr-4 primary-btn">Volver</v-btn>
            <v-btn @click="save()" elevation="2" color="primary" dark class="primary-btn">Enviar</v-btn>
          </v-card-actions>
        </v-container>
      </v-main>
    </v-app>
    
  </div>
</template>

<script>
  export default {
    data: () => ({
      valid: false,
      categories: [],
      selectedFile: null,
      registerProduct: {
        id:'',
        product_name: '',
        description: '',
        quantity: '',
        price: '',
        status: 'Activo',
        image_url: '',
        category_id: '',
      },
    }),
    created() {
      this.getCategorias();
    },
    methods: {
      captureFileName() {
        if (this.selectedFile && this.selectedFile.name) {
          this.registerProduct.image_url = this.selectedFile.name;
        }
      },
      buttonBack(){
        this.$parent.backComponent();
        this.$parent.list();
      },
      showProducts(){
        this.$parent.list();
      },
      getCategorias() {
        axios.get('/ExtractCategories').then(respuesta => {
          this.categories = respuestas;
        }).catch((error) => {
          console.error('Error al obtener la lista de categorías: ', error);
        });
      },
      save() { 
        if (!this.registerProduct.product_name || !this.registerProduct.description || !this.registerProduct.quantity || 
        !this.registerProduct.price || !this.registerProduct.image_url || !this.registerProduct.status || !this.registerProduct.category_id) {
          swal({
            title: "Campos Vacíos",
            text: "Por favor complete todos los campos",
            icon: "error",
            button: "Aceptar",
          });
          return;
        }

        axios.post('/registerProduct', this.registerProduct)
        .then(respuesta => {
          if (respuesta.data.status) {
            console.log("Registro exitoso");
            swal({
              title: "Registro Exitoso",
              text: "El producto se registró correctamente",
              icon: "success",
              button: "Aceptar",
            });
            this.registerProduct.product_name = null;
            this.registerProduct.description = null;
            this.registerProduct.quantity = null;
            this.registerProduct.price = null;
            this.registerProduct.image_url = null;
            this.registerProduct.status = null;
            this.registerProduct.category_id = null;
            this.$parent.backComponent();
            this.showProducts();
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
      },
    },
  }
</script>
