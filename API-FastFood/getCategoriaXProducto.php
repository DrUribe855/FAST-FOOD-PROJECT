<?php 
	header("Access-Control-Allow-Origin: * "); // Permite el acceso desde cualquier origen, o usa "http://localhost" si solo quieres permitirlo desde localhost.
	header("Access-Control-Allow-Methods: GET, POST");
	header("Access-Control-Allow-Headers: Content-Type");
    include 'Conexion.php';
    if (!empty($_GET['id_categoria'])) {
        $id_categoria = $_GET['id_categoria'];
	    $consulta_preguntas = $base_de_datos->prepare("SELECT * FROM products WHERE category_id = $id_categoria");
        $consulta_preguntas->execute();
        $datos = $consulta_preguntas->fetchAll(PDO::FETCH_ASSOC);
        if($datos){
            $respuesta = [
                'status' => true,
                'message' => 'OK',
                'respuesta' => $datos
            ];
               
            echo json_encode($respuesta);
        }
	}else{
        $respuesta = [
                        'status' => false,
                        'mesagge' => "ERROR##DATOS##GET",
                        '$_GET' => $_GET,
                        '$_POST' => $_POST
                    ];
        echo json_encode($respuesta);
    }
?>