<?php
	include "Conexion.php";

	if ( (!empty($_POST["campo_correo"]) && !empty($_POST["campo_password"])) || ( !empty($_GET['campo_correo']) && !empty($_GET['campo_password']) ) ) {
		
		$email = (!empty($_POST['campo_correo']))? $_POST['campo_correo'] : $_GET['campo_correo'];
		$password = (!empty($_POST['campo_password']))? MD5($_POST['campo_password']) : MD5($_GET['campo_password']);

		$consulta = $base_de_datos->prepare("SELECT name FROM users WHERE email = :cor AND password = :pas");
		$consulta->bindParam(":cor", $email);
		$consulta->bindParam(":pas", $password);
		$consulta->execute();

		$datos = $consulta->fetchAll(PDO::FETCH_ASSOC);
		$datos = mb_convert_encoding($datos, "UTF-8", "iso-8859-1");
		if($datos){
			$respuesta = [
				'status' => true,
				'message' => "USER##FOUND",
				'user' => $datos[0]
			];

			echo json_encode($respuesta);

		}else{
			$respuesta = [
				'status' => false,
				'message' => "USER##NOT##FOUND"
			];

			echo json_encode($respuesta);
		}

	}else{
		$respuesta = [
			'status' => false,
			'message' => "ERROR##DATA"
		];

		echo json_encode($respuesta);
	}
?>