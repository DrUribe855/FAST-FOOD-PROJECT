<template>
  <div>
    <v-btn
        @click="buttonBack()"
        elevation="2"
    >Volver</v-btn>
    <v-app>
    <v-main>
      <v-form v-model="valid">
        <v-container>
          <v-row>
            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="registerProduct.product_name"
                label="Nombre"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="registerProduct.description"
                label="Descripcion"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="registerProduct.quantity"
                label="Cantidad"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="registerProduct.price"
                label="Precio"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
            <!-- <v-file-input
              show-size
              counter
              multiple
              label="File input"
              v-model="registerProduct.image_url"
              required
            ></v-file-input> -->
            <v-file-input
              truncate-length="50"
              label="File input"
              v-model="selectedFile"
              @change="captureFileName"
              required
            ></v-file-input>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="registerProduct.status"
                label="Estado"
                required
              ></v-text-field>
            </v-col>

            <v-col
              class="d-flex"
              cols="12"
              sm="6"
            >
            <v-select
              v-model="registerProduct.category_id"
              :items="categories"
              label="Categoria"
              item-text="category_name"
              item-value="id"
            ></v-select>
            </v-col>


          </v-row>
          <v-btn
          @click="save()"
              elevation="2"
          >Enviar</v-btn>
        </v-container>
      </v-form>
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
        // console.log("Datos del categoria", this.categories);
    },
    methods: {
      captureFileName() {
        // Verifica si se ha seleccionado un archivo
        if (this.selectedFile && this.selectedFile.name) {
          this.registerProduct.image_url = this.selectedFile.name; // Almacena solo el nombre del archivo
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
        // console.log("Datos del proveedor", this.categories);
        axios.get('/ExtractCategories').then(respuesta => {
            this.categories = respuesta.data.categories;
        }).catch((error) => {
            console.error('Error al obtener la lista de categorias: ', error);
        });
      },

      save() { 
        // Validar campos vacíos
        if (!this.registerProduct.product_name || !this.registerProduct.description || !this.registerProduct.quantity || 
        !this.registerProduct.price || !this.registerProduct.image_url || !this.registerProduct.status || !this.registerProduct.category_id) {
            swal({
                title: "Campos Vacíos",
                text: "Por favor complete todos los campos",
                icon: "error",
                button: "Aceptar",
            });
            return; // Detener la ejecución del método si hay campos vacíos
        }

        // Si todos los campos están completos, enviar la solicitud al servidor
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
                //Limpiar los campos de entrada
                this.registerProduct.product_name = null;
                this.registerProduct.description = null;
                this.registerProduct.quantity = null;
                this.registerProduct.price = null;
                this.registerProduct.image_url = null;
                this.registerProduct.status = null;
                this.registerProduct.category_id = null;
                this.$parent.backComponent();
                this.showProducts();
                //this.desserts = respuesta.data.machineryData;
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
