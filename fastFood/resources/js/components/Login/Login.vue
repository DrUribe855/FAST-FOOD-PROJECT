<template>
	<div class="col-12 row m-0 p-0">
		<section class="row m-0 p-0">
			<section class="col-6 border left vh-100"></section>
			<section class="col-6 border right vh-100"></section>
		</section>
		<form-login :class="['col-3', 'content-center', 'border', 'shadow mb-5']"></form-login>
	</div>
</template>
<script>
import swal from 'sweetalert';
import FormLogin from './FormLogin.vue'

export default{
	components: {
		'form-login': FormLogin,
	},
	data(){
		return{
		}
	},
	methods: {
		
	}
}
</script>