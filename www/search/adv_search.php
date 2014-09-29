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
	<li><a class="nav3" href="../games.php">Games</a></li>
	<li><a class="nav4" href="../about_us.php">About Us</a></li>
</ul>
</header>
<body class="home">
<div class="login">
<?php
if(! ini_get('date.timezone') )
{
	date_default_timezone_set('America/New_York');
}

include '../core.php';
require '../dbconnect.php';

if(loggedin()){
	$firstname = getfield('firstname');
	$surname = getfield('surname');
	$rank = rank($_SESSION['user_name']);
	echo "You are logged in $rank $firstname.";
?>
<form method="link" action="../login/logout.php"> 
	<input type="submit" value="Logout Here"></form>
</div>
<div id="div1">
<br><br><br><br>
<table>
<tr>
<th><u>Search Posts:</u></th>
</tr>
<tr>
<td>
<form method="post" action="adv_search.php">
<br>Search by:
	<select name="search_by">
		<option value="title">title</option>
		<option value="message">message</option>
	</select>
	<br>Search criteria #1:<input type="text" name="sfirst"/>
	<br>Search criteria #2 (optional):<input type="text" name="sSecond"/>
	<br>Exclude (optional):<input type="text" name="exclude" /><br />
<input type="submit" value="Submit" />
</form>
</td>
</tr>
<tr>
<th><u>Search Results:</u></th>
<tr>
<td>
<?php
    if (!empty($_POST['search_by'])) {
		$criteria = $_POST['search_by'];
	}
	else{
		$criteria = 'title';
	}
	if (!empty($_POST['sfirst'])) {
		$first = $_POST['sfirst'];
		if (!empty($_POST['sSecond'])) {
			$second = $_POST['sSecond'];
			if (!empty($_POST['exclude'])) {
				$exclude = $_POST['exclude'];
				$result = pg_query($db,"Select s1.id as id, s1.title as title, s1.poster as poster FROM (SELECT id, title, poster FROM anon.posts WHERE $criteria LIKE '%$first%' OR $criteria LIKE '%$second%') as s1 NATURAL JOIN (SELECT id, title, poster FROM anon.posts WHERE $criteria NOT LIKE '%$exclude%') as s2 ORDER BY $criteria;");
			}
			else{
				$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE $criteria LIKE '%$first%' OR $criteria LIKE '%$second%' ORDER BY $criteria");
			}
			
			if (!pg_num_rows($result)) {
				echo "<br>This search yielded no results<br>";
			} 
			else {
				echo "<br><table border=1 cellpadding = 5><tr><th><u>Search results:</u></th></tr>";
				echo "<tr><th>Author:</th><th>Title:</th></tr>";
				while ($row = pg_fetch_array($result,NULL,PGSQL_ASSOC)) {
				   echo "<tr><td>$row[poster]</td><td><a href=\"../posts/read.php?MBID=$row[id]\"> $row[title]</a></td></tr>";
				}
				echo "</table><br>";
			}
		}
		else{
			if (!empty($_POST['exclude'])) {
				$exclude = $_POST['exclude'];
				$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE $criteria LIKE '%$first%' ORDER BY $criteria') as s1 NATURAL JOIN (SELECT id, title, poster FROM anon.posts WHERE $criteria NOT LIKE '%$exclude%') as s2 ORDER BY $criteria;");
			}
			$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE $criteria LIKE '%$first%' ORDER BY $criteria;");
			if (!pg_num_rows($result)) {
				echo "<br>This search yielded no results<br>";
			} 
			else {
				echo "<br><table border=1 cellpadding = 5><tr><th><u>Search results:</u></th></tr>";
				echo "<tr><th>Author:</th><th>Title:</th></tr>";
				while ($row = pg_fetch_array($result,NULL,PGSQL_ASSOC)) {
				   echo "<tr><td>$row[poster]</td><td><a href=\"../posts/read.php?MBID=$row[id]\"> $row[title]</a></td></tr>";
				}
				echo "</table><br>";
			}
		}		
	}
	else{
		echo "Enter something to search for.";
	}
	echo "</td></tr></table><br><br>";
?>
</div>
<div id="div2">
<?php 
	echo "<br><br><br><br>";
	friends($here);
	echo "<br><br><br><br>";
	chat3($here);
}
else{
	include '../login/login_form.php';
	echo '</div><br><br><h2 class="SecondHeader"> Please login to view this page. </h2>';
}
 ?>
 </body>
 </html>