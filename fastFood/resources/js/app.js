require('./bootstrap');

import Vue from 'vue';
import Vuetify from "vuetify";

Vue.use(Vuetify);
 
window.Vue = require('vue');

// Usuarios
Vue.component('login', require('./components/Login/Login.vue').default);
// Categories
Vue.component('categorie', require('./components/Category/Category.vue').default);
// usuarios
Vue.component('user-register', require('./components/User/userRegistration.vue').default);
// Productos
Vue.component('show-products', require('./components/Products/ShowProducts.vue').default);
// Ventas
Vue.component('bills', require('./components/Bills/Bills.vue').default);


const app = new Vue({
    el: '#app',
    vuetify: new Vuetify(),
});
