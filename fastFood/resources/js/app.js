require('./bootstrap');

import Vue from 'vue';
import Vuetify from "vuetify";

Vue.use(Vuetify);
 
window.Vue = require('vue');

//Login
Vue.component('login', require('./components/Login.vue').default);

// Usuarios
Vue.component('user-register', require('./components/User/userRegistration.vue').default);

//Categorias
Vue.component('register-category', require('./components/Categories/RegisterCategory.vue').default);

// Productos
Vue.component('consult-product', require('./components/Products/Products.vue').default);
Vue.component('show-products', require('./components/Products/ShowProducts.vue').default);

const app = new Vue({
    el: '#app',
    vuetify: new Vuetify(),
});
