<template>
	<div class="login-container background-container">
		<div>
			<form>
				<div class="title">
					<h2 class="">Iniciar sesión</h2>
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput" class="form-label">Correo electrónico</label>
					<input v-model="email" type="email" class="form-control" id="formGroupExampleInput" placeholder="Ingrese su correo electrónico">
				</div>
				<div class="mb-3">
					<label for="formGroupExampleInput2" class="form-label">Contraseña</label>
					<input v-model="password" type="password" class="form-control" id="formGroupExampleInput2" placeholder="Ingrese su contraseña">
				</div>
				<div class="small-text">
					<b>¿No tienes una cuenta? <a class="btn-register" @click="$parent.goToRegister()">Regístrate</a></b>
				</div>
				<div class="button-container">
					<button class="button-login" type="button" @click="login()">Ingresar</button>
				</div>
			</form>
		</div>
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
				window.location.href = '/viewProducts';
				
			}).catch(error => {
				console.log("Error en login");
				console.log(error.response);
				if(error.response.data.message == "Datos incompletos"){
					this.showAlert("Error", "Alguno de los campos no fue diligenciado", "error");
				}else if(error.response.data.message == "The email must be a valid email address."){
					this.showAlert("Error", "Ingrese un correo electronico válido", "error");
				}else if(error.response.data.message == "Datos incorrectos"){
					this.showAlert("Error", "Alguno de los datos diligenciados es incorrecto");
				}
			});
		},
		showAlert(title, text, icon){
        swal({
          title: title,
          text: text,
          icon: icon,
        });
      }
	}
}
</script>
