<template>
  <div>
    <v-btn
        v-if="listProducts"
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
                v-model="product_name"
                :rules="nameRules"
                :counter="10"
                label="Nombre"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="description"
                :rules="nameRules"
                :counter="10"
                label="Descripcion"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="quantity"
                :rules="emailRules"
                label="Cantidad"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="price"
                :rules="emailRules"
                label="Precio"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="image_url"
                :rules="emailRules"
                label="Imagen"
                required
              ></v-text-field>
            </v-col>

            <v-col
              cols="12"
              md="4"
            >
              <v-text-field
                v-model="status"
                :rules="emailRules"
                label="Estado"
                required
              ></v-text-field>
            </v-col>

          </v-row>
          <v-btn
              elevation="2"
          >Enviar</v-btn>
        </v-container>
      </v-form>
    </v-main>
  </v-app>
  <!-- <listProductos v-if="listProducts"></listProductos> -->
  </div>
</template>
<script>
  import ShowProducts from "./ShowProducts.vue";
  export default {
    components: {
      'productos': ShowProducts,
    },
    data() {
      return {
          listProducts: true,
      }
    },
  }
</script>
