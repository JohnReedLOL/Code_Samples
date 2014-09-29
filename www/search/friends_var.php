<?php
	require '../core.php';
	$return = $_POST['return'];
	if($_SESSION['friends_var'] == 0 || !isset($_SESSION['friends_var'])){
		$_SESSION['friends_var'] = 1;
	}
	else{
		$_SESSION['friends_var'] = 0;
	}
	header("Location: $return");
?>