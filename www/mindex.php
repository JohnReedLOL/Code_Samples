<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
	<title>Matrix Rain</title>
	<link rel="stylesheet" href="style.css">
  <style>
	*{margin: 0; padding: 0;}
	body, html {height: 100%; }
	canvas {width:100%;height:100%;}
	#canvas-wrap { position:relative; } 
	#overlay{ position:absolute; top:125px; left:725px;background-color:#EBF4CB;padding:10px;border-radius:10px; }
	#overlay2{ position:absolute; top:125px; left:220px;background-color:#EBF4CB;padding:10px;border-radius:10px; }
	#overlay3{ position:absolute; top:200px; left:220px;background-color:#EBF4CB;padding:10px;border-radius:10px; }
  </style>
  <header class="1">
<img class="logo" src="logoMKIII.png" width = "150" height="75">
<br>
<h1 class="header"> Welcome to Anon: Anonymous World </h1>
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
</head>
<body onload="for(s=window.screen,w=q.width=s.width,h=q.height,m=Math.random,p=[],i=0;i<256;p[i++]=1);
setInterval('9Style=\'rgba(0,0,0,.05)\'9Rect(0,0,w,h)9Style=\'#0F0\';p.map(function(v,i){9Text(String.fromCharCode(3e4+m()*33),i*10,v);p[i]=v>758+m()*1e4?0:v+10})'.split(9).join(';q.getContext(\'2d\').fill'),33)">
<div id="canvas-wrap">
  <canvas id=q width="800" height="1500"></canvas>
  <div id="overlay2">
	<h2>Enjoy The Site!</h2>
  </div>
  <div id="overlay3">
	<img src="Koala.jpg" height="400" width="400" style="border: 1px black solid;">
  </div>
  <div id="overlay">
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
<form method="link" action="login/logout_code.php"> 
	<input type="submit" value="Logout Here"></form>
</div>
<?php
	friends($here);
	chat($here);	
?>
<div class="loginform">
<?php
}
else{
	include 'login/login_form.php';
	echo '</div><h1 style ="text-align:center;"> Please login to view this page. </h1>';
}
?>
</div>
  </div>
</div>
</body>
</html>