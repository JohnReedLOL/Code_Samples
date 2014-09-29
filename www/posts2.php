<html>
<title> Anonymous World </title>
<link rel="stylesheet" href="style.css">
<header class="1">
<img class="logo" src="logoMKIII.png" width = "150" height="75">
<br>
<h1 class="header"> Posts </h1>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<ul class="navbar">
	<li><a class="nav1" href="index.php">Home</a></li>
	<li><a class="nav2" href="posts.php">Posts</a></li>
	<li><a class="nav3" href="privacy.php">Privacy</a></li>
	<li><a class="nav3" href="about_us.php">About Us</a></li>
	<li><a class="nav4" href="games.php">Games</a></li>
</ul>
<div class="login">
	<?php
	if(! ini_get('date.timezone') )
	{
		date_default_timezone_set('GMT');
	}
	include 'core.php';
	require 'dbconnect.php';
	if(loggedin()) {
		$firstname = getfield('firstname');
		$surname = getfield('surname');
		$rank = rank($_SESSION['user_name']);
		echo "You are logged in $rank $firstname.";
	?>
	<form method="link" action="login/logout.php"> 
		<input type="submit" value="Logout Here"></form>
</div>
</header>
<body class="posts">
<br><br>
</body>
<div id="div1">
<div class="search">
	<?php
	echo "<br /><br /><a href=\"../posts/post.php\">Post something</a>";
	?>
	<form method="post" action="posts.php">
	<?php
	echo "<a href=\"../search/adv_search.php\">Advanced Search</a><br />";

	?>
	<br>View: <select name="posts">
			<option value="X">X</option>
			<option value="All">All</option>
			<option value="Oldest">Oldest</option>
			<option value="Newest">Newest</option>
			<option value="Most viewed">Popular</option>
			<option value="Least viewed">Less Popular</option>
			<option value="Friends">Friend's</option>
			</select>
		<br>Basic search:<input type="text" name="search" /><br />
		<input type="submit" value="Search" />
	</form>
	<?php
		if (!empty($_POST['search'])) {
			basic_search($_POST['search']);
		}
		else if (!empty($_POST['posts']) && $_POST['posts'] != 'X') {
			view_search($_POST['posts']);
		}
		else if (isset($_SESSION['prev_search'])) {
			basic_search($_SESSION['prev_search']);
		}
		else if (isset($_SESSION['prev_posts'])) {
			view_search($_SESSION['prev_posts']);
		}
		else{
			echo "Enter a search to begin.";
		}
	?>
</div>
</div>
<div id="div3">
<div class="friends">
	<?php
		friends($here);
	?>
</div>
</div>
<div id="div4">
<div class="chat">
	<?php
		chat($here);
	}	
	?>
</div>
</div>
<div class="loginform">
	<?php
	else {
		include 'login/login_form.php';
	}
	?>
</div>
</body>
<!--
<address>
	Updated 16 April 2014 by a Ryan.
</address>
-->
</html>
