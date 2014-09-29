<?php
	require 'core.php';
	$return = $_POST['return'];
	if($_SESSION['posts_var'] == 0 || !isset($_SESSION['posts_var'])){
		$_SESSION['posts_var'] = 1;
	}
	else{
		$_SESSION['posts_var'] = 0;
	}
	header("Location: $return");
?>