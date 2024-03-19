require('./bootstrap');

import Vue from 'vue';
import Vuetify from "vuetify";

Vue.use(Vuetify);
 
window.Vue = require('vue');

Vue.component('login', require('./components/Login.vue').default);
Vue.component('categorie', require('./components/categorie/Categorie.vue').default);

const app = new Vue({
    el: '#app',
    vuetify: new Vuetify(),
});
