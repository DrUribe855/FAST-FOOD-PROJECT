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
                    <v-spacer></v-spacer>
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
                                <v-text-field
                                  v-model="editedItem.category_id"
                                  label="Categoria"
                                ></v-text-field>
                              </v-col>
                              <v-col
                                cols="12"
                                sm="6"
                                md="4"
                              >
                                <v-text-field
                                  v-model="editedItem.status"
                                  label="Estado"
                                ></v-text-field>
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
                            Cerrar
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                    <v-dialog v-model="dialogDelete" max-width="500px">
                      <v-card>
                        <v-card-title class="text-h5">Are you sure you want to delete this item?</v-card-title>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
                          <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
                          <v-spacer></v-spacer>
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
                  <v-icon
                    small
                    @click="deleteItem(item)"
                  >
                    mdi-delete
                  </v-icon>
                </template>
                <template v-slot:no-data>
                  <v-btn
                    color="primary"
                    @click="list"
                  >
                    Reset
                  </v-btn>
                </template>
              </v-data-table>
            </div>
        </v-main>
    </v-app>

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
          dialog: false,
          dialogDelete: false,
          headers: [
            { text: 'Nombre', value: 'product_name' },
            { text: 'Descripción', value: 'description' },
            { text: 'Precio', value: 'price' },
            { text: 'Categoría', value: 'category_id' },
            { text: 'Estado', value: 'status' },
            { text: 'Actions', value: 'actions', sortable: false },
          ],
          desserts: [],
          editedIndex: -1,
          editedItem: {
            product_name: '',
            description: '',
            price: 0,
            status: 0,
            protein: 0,
          },
          defaultItem: {
            name: '',
            calories: 0,
            fat: 0,
            carbs: 0,
            protein: 0,
          },
        };
    },
    created() {
        this.list();
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
                this.desserts = respuesta.data.showproducts;
            }).catch(error => {
                console.log("Error en servidor");
                console.log(error);
                console.log(error.response);
            });
        },
        editItem (item) {
            this.editedIndex = this.desserts.indexOf(item)
            this.editedItem = Object.assign({}, item)
            this.dialog = true
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

        save () {
            if (this.editedIndex > -1) {
              Object.assign(this.desserts[this.editedIndex], this.editedItem)
            } else {
              this.desserts.push(this.editedItem)
            }
            this.close()
        },
    },
};
</script>