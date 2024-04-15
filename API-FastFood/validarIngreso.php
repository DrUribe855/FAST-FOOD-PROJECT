<?php
	include "Conexion.php";

	if ( (!empty($_POST["email"]) && !empty($_POST["password"])) || ( !empty($_GET['email']) && !empty($_GET['password']) ) ) {
		
		$email = (!empty($_POST['email']))? $_POST['email'] : $_GET['email'];
		$password = (!empty($_POST['password']))? $_POST['password'] : $_GET['password'];

		$consulta = $base_de_datos->prepare("SELECT id, name FROM users WHERE email = :cor AND password = :pas");
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