<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
	<title>Matrix Rain</title>
  <style>
	*{margin: 0; padding: 0;}
	body, html {height: 100%; }
	canvas {width:100%;height:100%;}
	#canvas-wrap { position:relative; } 
	#overlay{ position:absolute; top:325px; left:525px;background-color:#eeeeee;padding:10px;border-radius:10px; }
	#overlay2{ position:absolute; top:20px; left:350px;background-color:#eeeeee;padding:10px;border-radius:10px; }
  </style>
</head>
<body onload="for(s=window.screen,w=q.width=s.width,h=q.height=s.height,m=Math.random,p=[],i=0;i<256;p[i++]=1);
setInterval('9Style=\'rgba(1,1,1,.05)\'9Rect(0,0,w,h)9Style=\'#0F0\';p.map(function(v,i){9Text(String.fromCharCode(3e4+m()*33),i*10,v);p[i]=v>758+m()*1e4?0:v+10})'.split(9).join(';q.getContext(\'2d\').fill'),33)">
<div id="canvas-wrap">
  <canvas id=q width="800" height="600"></canvas>
  <div id="overlay">
	<?php include 'registration_complete.php'; ?>
  </div>
</div>
</body>
</html>