<html>
<title> Anonymous World </title>
<link rel="stylesheet" href="../style.css">
<header class="1">
<img class="logo" src="../logoMKIII.png" width = "150" height="75">
<br>
<h1 class="header"> Advanced Search </h1>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<ul class="navbar">
	<li><a class="nav1" href="../index.php">Home</a></li>
	<li><a class="nav2" href="../posts.php">Posts</a></li>
	<!--<li><a class="nav3" href="privacy.php">Privacy</a></li>-->
	<li><a class="nav3" href="../about_us.php">About Us</a></li>
	<li><a class="nav4" href="../games.php">Games</a></li>
</ul>
</div>
</header>
<body class="home">
<?php
require "../core.php";
require "../dbconnect.php";

if(!loggedin()){
	if(isset($_POST['username']) && isset($_POST['password']) && isset($_POST['password_again']) && isset($_POST['firstname']) && isset($_POST['surname'])){
		$username = $_POST['username'];
		$password = $_POST['password'];
		$password_again = $_POST['password_again'];
		$pass_hash = md5($password);
		$firstname = $_POST['firstname'];
		$surname = $_POST['surname'];
		if(!empty($username) && !empty($password) && !empty($password_again) && !empty($firstname) && !empty($surname)){
			if(strlen($username)>30 || strlen($firstname)>40 || strlen($surname)>30){
				echo 'Please adhear to maxlength of fields.';
			}
			else{
				if($password!=$password_again){
					echo "Passwords do not match.";
				}
				else{
					$query = "SELECT username FROM anon.users WHERE username = '$username'";
					$result = pg_query($db,$query);
					if(pg_num_rows($result) == 1){
						echo "The username $username already exists";
					}
					else{
						$query = "INSERT INTO anon.users (username, password, firstname, surname) VALUES ('".pg_escape_string($username)."','".pg_escape_string($pass_hash)."','".pg_escape_string($firstname)."','".pg_escape_string($surname)."')";
						if($result = pg_query($db,$query)){
							header('Location: ../login/r_complete_matrix.php');
						}
						else{
							echo "Sorry we could not register to you at this time. Try again later.";
						}
					}
				}
			}		
		}
		else{
			echo "All fields are required.";
		}
	}
?>
<div id="div1">
<br><br><br><br><br><br>
<h2><u>Registration Form:</u></h2>
<form action="login_registration.php" method="POST">
	Username:<br><input type="text" name="username" maxlength = "30" value = "<?php if(isset($username)){ echo $username;} ?>"><br><br>
	Password:<br><input type="password" name="password"><br><br>
	Password again:<br><input type="password" name="password_again"><br><br>
	Firstname:<br><input type="text" name="firstname" maxlength = "40" value = "<?php if(isset($firstname)){ echo $firstname;} ?>"><br><br>
	Surname:<br><input type="text" name="surname" maxlength = "40" value = "<?php if(isset($surname)){ echo $surname;} ?>"><br><br>
	<input type="submit" value="Register">
</form>
</div>
<?php	
}
else{
	echo "You are already logged in.";
}
?>
</div>
</body>
</html>