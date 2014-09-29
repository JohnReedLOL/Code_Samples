<html>
<title> Anonymous World </title>
<link rel="stylesheet" href="style.css">
<header class="1">
<img class="logo" src="logoMKIII.png" width = "150" height="75">
<br>
<h1 class="header">Posts</h1>
<div id="left"></div>
<div id="right"></div>
<div id="top"></div>
<div id="bottom"></div>
<ul class="navbar">
	<li><a class="nav1" href="index.php">Home</a></li>
	<li><a class="nav2" href="posts.php">Posts</a></li>
	<!--<li><a class="nav3" href="privacy.php">Privacy</a></li>-->
	<li><a class="nav3" href="games.php">Games</a></li>
	<li><a class="nav4" href="about_us.php">About Us</a></li>
</ul>
</header>
<body class="posts">
<div class="login">
<?php
if(! ini_get('date.timezone') )
{
	date_default_timezone_set('America/New_York');
}

include 'core.php';
require 'dbconnect.php';

if(loggedin()){
	$firstname = getfield('firstname');
	$surname = getfield('surname');
	$rank = rank($_SESSION['user_name']);
	echo "You are logged in $rank $firstname.";
?>
<form method="link" action="login/logout.php"> 
	<input type="submit" value="Logout Here"></form>
</div>
<br><br><br><br>
<div id="div1">
<table>
<tr>
<th> <u>Search Posts:<u></th>
</tr>
<tr>
<td>
<?php
echo '<br><form method="link" action="../search/adv_search.php"> 
	<input type="submit" value="Advanced Search"></form>';
?>
<form method="post" action="posts.php">
	<br>View:
	<select name="posts">
		<option value="X" selected></option>
		<option value="All">All</option>
		<option value="Oldest">Oldest</option>
		<option value="Newest">Newest</option>
		<option value="Most viewed">Popular</option>
		<option value="Least viewed">Less Popular</option>
		<option value="Following">Following</option>
	</select>
	<br>Basic search:<input type="text" name="search" /><br />
	<input type="submit" value="Search" />
	<br><br>
</form>
</td>
</tr>
<tr>
<th><u>Search Results:</u></th>
<tr>
<td>
<?php
	//echo "<a href=\"../p1/search/adv_search.php\">Advanced Search</a><br />";
	//echo "<a href=\"../search/adv_search.php\">Advanced Search!</a><br />";
	if (!empty($_POST['search']) && isset($_SESSION['search'])) {
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
		echo "</td></tr></table><br><br>";
?>
</div>
<div id="div2" style="width: 900px;">
	<?php
	postForm($here);
	echo "<br><br>";
	friends($here);
	echo "<br><br>";
	chat($here);
	echo "<br><br>";
}
else{
	include 'login/login_form.php';
	echo '</div><br><br><h2 class="SecondHeader">Please login to view this page. </h2>';
}
?>
</div>
</body>
</html>
