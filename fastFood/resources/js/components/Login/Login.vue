<template>
	<div>
		<form>
			<h1>Formulario de inicio de sesión</h1>
			<input type="email" name="email" placeholder="Email" v-model="email">
			<input type="password" name="pass" placeholder="Password" v-model="password">
			<button @click="login()" type="button">Ingresar</button>
		</form>
	</div>
</template>
<script>
import swal from 'sweetalert';

export default{
	data(){
		return{
			email: '',
			password: '',
			error: false,
		}
	},
	methods: {
		login(){
			axios.post('/login', {
				email: this.email,
				password: this.password
			}).then(response => {
				console.log(response);
			}).catch(error => {
				console.log("Error en login");
				console.log(error.response);
			});
		}
	}
}
</script>