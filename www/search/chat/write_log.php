<?php
include_once '../../core.php';
//header("Location: ../../log.html");
if(isset($_POST['text'])){
    $text = $_POST['text']; 
    $fp = fopen("../../log.html", 'a') or exit("Unable to open file!");
	date_default_timezone_set('America/New_York');
	$time = time();
    fwrite($fp, "<div class='msgln'>(".date("g:i A",$time).") <b>".$_SESSION['chat']."</b>: ".stripslashes(htmlspecialchars($text))."<br></div>");
    fclose($fp);
}