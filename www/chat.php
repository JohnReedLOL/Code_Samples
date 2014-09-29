<?php
	require 'core.php';
	$return = $_POST['return'];
	if($_SESSION['chat'] == 0){
		$_SESSION['chat'] = 1;
	}
	else{
		$_SESSION['chat'] = 0;
	}
	header("Location: $return");
?>