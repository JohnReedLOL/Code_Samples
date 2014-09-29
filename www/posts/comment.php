<?php
	require '../core.php';
	$return = $_POST['return'];
	if($_SESSION['comment'] == 0 || !isset($_SESSION['comment'])){
		$_SESSION['comment'] = 1;
	}
	else{
		$_SESSION['comment'] = 0;
	}
	header("Location: $return");
?>