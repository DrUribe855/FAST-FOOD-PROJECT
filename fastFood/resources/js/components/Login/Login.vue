<template>
	<div class="col-12 row m-0 p-0">
		<section class="row m-0 p-0">
			<section class="col-6 border left vh-100"></section>
			<section class="col-6 border right vh-100"></section>
		</section>
		<form-login v-if="login" :class="['col-3', 'content-center', 'shadow mb-5']"></form-login>
		<user-registration v-if="!login" :class="['col-3', 'content-center']"></user-registration>
	</div>
</template>
<script>
import swal from 'sweetalert';
import FormLogin from './FormLogin.vue'
import RegisterForm from '../User/userRegistration.vue'

export default {
	components: {
		'form-login': FormLogin,
		'user-registration': RegisterForm,
	},
	data(){
		return{
			login: true,
		}
	},
	methods: {
		goToRegister(){
			this.login = false;
		},
		backToLogin(){
			this.login = true;
		},
	}
}
</script>