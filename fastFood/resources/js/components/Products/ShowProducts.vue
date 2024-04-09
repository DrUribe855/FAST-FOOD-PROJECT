<template>
    <v-app>
        <v-main>
            <div>
              <v-data-table
                :headers="headers"
                :items="desserts"
                sort-by="calories"
                class="elevation-1"
              >
                <template v-slot:top>
                  <v-toolbar
                    flat
                  >
                    <v-toolbar-title>Listado de productos</v-toolbar-title>
                    <v-divider
                      class="mx-4"
                      inset
                      vertical
                    ></v-divider>
                    <h6 v-if="id">{{ id.category_name.toUpperCase() }}</h6>
                    <v-spacer></v-spacer>
                    <v-btn v-if="id" 
                      color="primary"
                      class="mb-2 mr-2"
                      @click="$parent.$parent.close()">
                      volver
                    </v-btn>
                    <v-dialog
                      v-model="dialog"
                      max-width="500px"
                    >
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn
                          color="primary"
                          dark
                          class="mb-2"
                          v-bind="attrs"
                          v-on="on"
                        >
                          Registrar producto
                        </v-btn>
                      </template>
                      <v-card>
                        <v-card-title>
                          <span class="text-h5">{{ formTitle }}</span>
                        </v-card-title>

                        <v-card-text>
                          <v-container>
                            <v-row>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                                <v-text-field
                                  v-model="editedItem.product_name"
                                  label="Nombre"
                                ></v-text-field>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                                <v-text-field
                                  v-model="editedItem.description"
                                  label="Descripción"
                                ></v-text-field>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                                <v-text-field
                                  v-model="editedItem.price"
                                  label="Precio"
                                ></v-text-field>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                                <v-select
                                label="Categoría"
                                :items="categories"
                                :key="editedItem.category_id"
                                v-model="editedItem.category_name"></v-select>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                              <v-select
                                label="Estado"
                                :items="status"
                                v-model="editedItem.status"></v-select>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                              <v-file-input truncate-length="50" label="File input" v-model="selectedFile" @change="captureFileName" required></v-file-input>
                              </v-col>
                            </v-row>
                          </v-container>
                        </v-card-text>

                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="close"
                          >
                            Cancelar
                          </v-btn>
                          <v-btn
                            color="blue darken-1"
                            text
                            @click="save"
                          >
                            Guardar
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </v-toolbar>
                </template>
                <template v-slot:item.actions="{ item }">
                  <v-icon
                    small
                    class="mr-2"
                    @click="editItem(item)"
                  >
                    mdi-pencil
                  </v-icon>
                </template>
              </v-data-table>
            </div>
        </v-main>
    </v-app>

</template>

<script>  
export default {
  props: ['dataCategory'],
    components: {
        
    },
    data() {
        return {
          status: [
            'Activo',
            'Inactivo'
          ],
          selectedFile: null,
          categories: [],
          dialog: false,
          dialogDelete: false,
          headers: [
            { text: 'Nombre', value: 'product_name' },
            { text: 'Descripción', value: 'description' },
            { text: 'Precio', value: 'price' },
            { text: 'Categoría', value: 'category_name' },
            { text: 'Estado', value: 'status' },
            { text: 'Actions', value: 'actions', sortable: false },
          ],
          desserts: [],
          editedIndex: -1,
          editedItem: {
            id: '',
            product_name: '',
            description: '',
            price: 0,
            status: '',
            category_name: '',
            image_url: '',
          },
          defaultItem: {
            name: '',
            calories: 0,
            fat: 0,
            carbs: 0,
            protein: 0,
          },
          id: '',
        };
    },
    created() {
      if (this.dataCategory != undefined) {
        this.id = this.dataCategory;
        this.list(false);
      }else{
        this.list(true);
      }
      this.getCategories();
    },
    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'Registro de producto' : 'Modificar producto'
      },
    },

    watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },
    methods: {
      captureFileName() {
        if (this.selectedFile && this.selectedFile.name) {
          this.editedItem.image_url = this.selectedFile.name;
        }
      },
      save() { 
        console.log(this.editedItem.category_name)
      if(this.formTitle === 'Registro de producto'){
        if (!this.editedItem.product_name || !this.editedItem.description || 
        !this.editedItem.price || !this.editedItem.status || !this.editedItem.category_name) {

          swal({
            title: "Campos Vacíos",
            text: "Por favor complete todos los campos",
            icon: "error",
            button: "Aceptar",
          });
          return;
        }
        console.log(this.editedItem);

        axios.post('/registerProduct', this.editedItem)
        .then(respuesta => {
          if (respuesta.data.status) {
            console.log("Registro exitoso");
            console.log(respuesta.data)
            swal({
              title: "Registro Exitoso",
              text: "El producto se registró correctamente",
              icon: "success",
              button: "Aceptar",
            });
            this.editedItem.product_name = null;
            this.editedItem.description = null;
            this.editedItem.price = null;
            this.editedItem.image_url = null;
            this.editedItem.status = null;
            this.editedItem.category_name = null;
            this.editedItem.category_id = null; // Restablecer category_id
            if (this.dataCategory != undefined) {
              this.id = this.dataCategory;
              this.list(false);
            }else{
              this.list(true);
            }
            this.close();
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
      }else{
        axios.put(`/UpdateProduct/${this.editedItem.id}`, this.editedItem).then(respuesta => {
            if (respuesta.data.status) {
              console.log("Actualización exitosa");
              console.log(respuesta.data),
              swal({
                  title: "Actualizacion Exitoso",
                  text: "El producto se actualizo correctamente",
                  icon: "success",
                  button: "Aceptar",
              });
              if (this.dataCategory != undefined) {
                this.id = this.dataCategory;
                this.list(false);
              }else{
                this.list(true);
              }
              this.close();
            } else {
              console.log("Error: Los datos están duplicados");
              swal({
                  title: "Error",
                  text: "Error verifica que todo este bien",
                  icon: "danger",
                  button: "Aceptar",
              });
            }
          }).catch(error => {
            swal({
                icon: 'error',
                title: 'Oops...',
                text: 'Algo Salio mal! (Existe)',
            });
            console.log("Error en servidor");
            console.log(error);
            console.log(error.response);
        });
      }
    },
    getCategories() {
      axios.get('/ExtractCategories').then(response => {
        response.data.categories.forEach(category => {
          this.categories.push(category.category_name);
        });
        console.log(this.categories)
      }).catch((error) => {
        console.error('Error al obtener la lista de categorías: ', error);
        console.log(error.response);
      });
    },
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
      list(type) {
        if (type) {
          console.log("Entre")
          axios.get('/showProducts').then(respuesta => {
              console.log("Respuesta del servidor");
              console.log(respuesta.data);
              this.desserts = respuesta.data.showproducts;
          }).catch(error => {
              console.log("Error en servidor");
              console.log(error);
              console.log(error.response);
          });
        }else{
          axios.get(`/consultProduct/${this.id.id}`).then(respuesta => {
              console.log("Respuesta del servidor");
              console.log(respuesta.data);
              this.desserts = respuesta.data.product;
          }).catch(error => {
              console.log("Error en servidor");
              console.log(error);
              swal("Error", "CLick", "danger");
              console.log(error.response);
          });
        }
      },
      editItem(item) {
        this.editedIndex = this.desserts.indexOf(item);
        this.editedItem = Object.assign({}, item);
        this.dialog = true;
      },
      deleteItem (item) {
        this.editedIndex = this.desserts.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },  
      deleteItemConfirm () {
        this.desserts.splice(this.editedIndex, 1)
        this.closeDelete()
      },  
      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },
    },
};
</script>