<!DOCTYPE html>
<html lang="en">
<head>
	<title>Log In</title>
	<link rel="stylesheet" href="forms.css">
</head>
<?php
if(! ini_get('date.timezone') )
{
	date_default_timezone_set('GMT');
}

echo "test!!!!!~~~~~~!!!!!~~~~~~!!!!!~~~~~~!!!!!~~~~~~v";
if(isset($_POST['username']) && isset($_POST['password'])){
	$username = $_POST['username'];
	$password = $_POST['password'];
	$CT = md5($_POST['password']);
	if(!empty($username) && !empty($password)){
		$query = "SELECT id,username FROM anon.users WHERE username = '".pg_escape_string($username)."' AND password = '".pg_escape_string($CT)."'";
		if($result = pg_query($db,$query)){
			$result_count = pg_num_rows($result);
			if($result_count == 0){
				echo "Invalid username/password combination";
			}
			else if($result_count == 1){
				$ans = pg_fetch_array($result,0,PGSQL_ASSOC);
				$user_id = $ans['id'];
				$user_name = $ans['username'];
				$_SESSION['user_id'] = $user_id;
				$_SESSION['user_name'] = $user_name;
				header('Location:../index.php');
			}
		}
	}
	else{
		echo "You left a field blank";
	}
	
}

?>
<form action="<?php echo $current_file; ?>" method="POST">
	<label for="username">Username:</label>
		<input type="text" name="username"> 
	Password: <input type="password" name="password">
	<input type="submit" value="Log in">
</form>
<br>
<?php echo "<a href= '/login/login_registration.php'>Register here</a>" ?>
