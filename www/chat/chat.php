<?php
	include_once '../core.php';
	$return = $_POST['return'];
	if($_SESSION['chat'] == 'X'){
		if($_POST['cname'] == 'anon'){
			$_SESSION['chat'] = 'Anonymous_'.generateRandomString(5);
		}
		else if($_POST['cname'] == 'rank'){
			$_SESSION['chat'] = rank($_SESSION['user_name']).'_'.generateRandomString(5);
		}
		else{
			$_SESSION['chat'] = $_SESSION['user_name'];
		}
	}
	else{
		$_SESSION['chat'] = 'X';
	}
	$r = $_SESSION['chat'];
	echo "$r";
	header("Location: $return");
?>