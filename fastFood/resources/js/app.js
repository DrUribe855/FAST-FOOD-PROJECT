require('./bootstrap');

import Vue from 'vue';
import Vuetify from "vuetify";

Vue.use(Vuetify);
 
window.Vue = require('vue');

<<<<<<< HEAD
Vue.component('login', require('./components/Login/Login.vue').default);
// usuarios

=======

// Usuarios
Vue.component('login', require('./components/Login/Login.vue').default);
// Categories
Vue.component('categorie', require('./components/Category/Category.vue').default);
// usuarios
>>>>>>> 4b7436d40cbc40f03c52454ee23348be30e60c5c
Vue.component('user-register', require('./components/User/userRegistration.vue').default);
// Productos
Vue.component('consult-product', require('./components/Products/Products.vue').default);
Vue.component('show-products', require('./components/Products/ShowProducts.vue').default);


const app = new Vue({
    el: '#app',
    vuetify: new Vuetify(),
});
