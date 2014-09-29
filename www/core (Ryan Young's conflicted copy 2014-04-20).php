<?php
ob_start();
session_start();
$current_file = $_SERVER['SCRIPT_NAME'];
$here  = $_SERVER['REQUEST_URI'];
$error = "Something terrible has occurred. Self-destruct initiated.";
if(isset($_SERVER['HTTP_REFERER']) && !empty($_SERVER['HTTP_REFERER'])){
	$http_referer = $_SERVER['HTTP_REFERER'];
}

function loggedin(){
	if(isset($_SESSION['user_id']) && !empty($_SESSION['user_id'])){
		return true;
	}
	else{
		return false;
	}
}

function getfield($field){
	require 'dbconnect.php';
	$query = "SELECT $field FROM anon.users WHERE id ='".$_SESSION['user_id']."'";
	if($result = pg_query($db,$query)){
		$ans = pg_fetch_array($result,0,PGSQL_ASSOC);
		return $ans["$field"];
	}
}

function updateAlias(){ //Not in use
	require 'dbconnect.php';
	$alias = md5(uniqid(rand(),true));
	$query = "SELECT alias FROM anon.users WHERE alias = '$alias'";
	if($result = pg_query($db,$query)){
		while(pg_num_rows($result) != 0){
			$alias = md5(uniqid(rand(),true));
			$result = pg_query($db,$query);
		}
		$query = "UPDATE anon.users SET alias = '$alias' WHERE id = $user_id";
		$result = pg_query($db,$query);
	}	
}
function chat($return){
	if(isset($_SESSION['chat'])){
		if($_SESSION['chat'] == 'X'){
			echo '
				<form method="post" action="chat/chat.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					Enter as:<br>
						Anonymous<input type="radio" name="cname" value="anon" checked /> <br>
						Rank<input type="radio" name="cname" value="rank" /><br>
						Named<input type="radio" name="cname" value="named" /><br>
					<input type="submit" value="Chat" /><br>
				</form>
			';
		}
		else{
			include "chat/cbox.php";
			echo '
				<form method="post" action="chat/chat.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Leave Chat" />
				</form>
			';
		}
	}
}

function generateRandomString($length) {
    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $randomString = '';
    for ($i = 0; $i < $length; $i++) {
        $randomString .= $characters[rand(0, strlen($characters) - 1)];
    }
    return $randomString;
}

function chat_2($return){
	if(isset($_SESSION['chat'])){
		if($_SESSION['chat'] == 0){
			echo '
				<form method="post" action="chat.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Enter Chat" />
				</form>
			';
		}
		else{
			echo '<embed wmode="transparent" src="http://www.xatech.com/web_gear/chat/chat.swf" quality="high" width="540" height="405" name="chat" FlashVars="id=207510466" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://xat.com/update_flash.php" /><br><small><a target="_BLANK" href="http://xat.com/web_gear/?cb">Get your own Chat Box!</a> <a target="_BLANK" href="http://xat.com/web_gear/chat/go_large.php?id=207510466">Go Large!</a></small><br>';
			echo '
				<form method="post" action="chat.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Leave Chat" />
				</form>
			';
		}
	}
}

function chat3($return){
	if(isset($_SESSION['chat'])){
		if($_SESSION['chat'] == 'X'){
			echo '
				<form method="post" action="../chat/chat.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					Enter as:<br>
						Anonymous<input type="radio" name="cname" value="anon" checked /> <br>
						Rank<input type="radio" name="cname" value="rank" /><br>
						Named<input type="radio" name="cname" value="named" /><br>
					<input type="submit" value="Chat" /><br>
				</form>
			';
		}
		else{
			include "../chat/cbox2.php";
			echo '
				<form method="post" action="../chat/chat.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Leave Chat" />
				</form>
			';
		}
	}
}

function comment($return,$pid){
	if(isset($_SESSION['comment'])){
		if($_SESSION['comment'] == 0){
			echo '
				<form method="post" action="comment.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Comment" />
				</form>
			';
		}
		else{
			$_SESSION['pid'] = $pid;
			//echo "$pid";
			include 'posts/comment_form.php';
		}
	}
}

function friends($return){
	if(isset($_SESSION['friends_var'])){
		if($_SESSION['friends_var'] == 0){
			echo '
				<form method="post" action="friends_var.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="View Followers/Following" />
				</form>
			';
		}
		else{
			include 'followers.php';
			$_SESSION['friend_origin'] = $return;
			echo '
				<form method="post" action="friends_var.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Close Followers List" />
				</form>
			';
		}
	}
}

function view_search($option){
	include 'dbconnect.php';
	$_SESSION['prev_posts'] = $option;
	if($option == 'Newest'){
		$result = pg_query($db,"SELECT * FROM anon.get_newest ORDER BY title;");
	}
	else if($option == 'Oldest'){
		$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE datesubmitted = (SELECT MIN(datesubmitted) as max FROM anon.posts) ORDER BY title;");
	}
	else if($option == 'Most viewed'){
		$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE views >= (SELECT AVG(views) as max FROM anon.posts) ORDER BY title;");
	}
	else if($option == 'Least viewed'){
		$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE views < (SELECT AVG(views) as min FROM anon.posts) ORDER BY title;");
	}
	else if($option == 'Friends'){
		$Name = $_SESSION['user_name'];
		$result = pg_query($db,"SELECT q1.id, q1.title, q1.poster FROM anon.posts as q1 JOIN (SELECT * FROM anon.friends WHERE friend_1 = '$Name' OR friend_2 = '$Name') as q2 ON (q1.poster = q2.friend_1 AND q2.friend_1 != '$Name') OR (q1.poster = q2.friend_2 AND q2.friend_2 != '$Name') ORDER BY q1.title;");
	}
	else{
		$result = pg_query($db,"SELECT id, title, poster FROM anon.posts ORDER BY title;");
	}
	if (!pg_num_rows($result)) {
		echo "<br>There are no posts - why not create one?<br>";
	} 
	else {
		echo "<br><table border=1 cellpadding = 5><tr><th><u>$option posts:</u></th></tr>";
		 echo "<tr><th>Author:</th><th>Title:</th></tr>";
		while ($row = pg_fetch_array($result,NULL,PGSQL_ASSOC)) {
			  echo "<tr><td>$row[poster]</td><td><a href=\"../posts/read.php?MBID=$row[id]\"> $row[title]</a></td></tr>";
		}
		echo "</table><br>";
	}	
}

function basic_search($search){
	include 'dbconnect.php';
	if(isset($_POST['search'])){
		$_SESSION['prev_search'] = $search;
		$search = $_POST['search'];
		$result = pg_query($db,"SELECT id, title, poster FROM anon.posts WHERE title LIKE '%$search%' ORDER BY title;");
		if (!pg_num_rows($result)) {
			echo "<br>There are no posts - why not create one?<br>";
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
		echo "Enter some search criteria.";
	}
}

function rank($Name){
	include 'dbconnect.php';
	$result = pg_query($db,"SELECT COUNT(following) as follower_count FROM (SELECT friend_1 as following FROM anon.friends WHERE friend_2 = '$Name')as followers;");
	if (!pg_num_rows($result)) {
		return "Neophyte";
	} 
	else if($Name == 'yar'){
		return "Harbinger";
	}
	else{
		$row = pg_fetch_array($result,NULL,PGSQL_ASSOC);
		if($row['follower_count'] < 5){
			return "Neophyte";
		}
		else if($row['follower_count'] < 10){
			return "Adherent";
		}
		else if($row['follower_count'] < 20){
			return "Speaker";
		}
		else if($row['follower_count'] < 40){
			return "Listener";
		}
		else if($row['follower_count'] < 80){
			return "Harbinger";
		}
		else{
			return "Harbinger";
		}
	}	
}
function postForm($return){
	include 'dbconnect.php';
	if(isset($_SESSION['posts_var'])){
		if($_SESSION['posts_var'] == 0){
			echo '
				<form method="post" action="posts_var.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Post Something" />
				</form>
			';
		}
		else{
			echo '<div style="background-color: #999945; width:500px; border:2px solid black; border-radius: 10px;">';
			if (isset($_POST['Title'])) {
			    if(isset($_POST['anonymous'])){
					$Name = "anonymous";
				}
				else{
					$Name = $_SESSION['user_name'];
				}
			    $Title = addslashes($_POST['Title']);
			    $Message = addslashes($_POST['Message']);
				$viewcnt = $_POST['viewcnt'];
			    $CurrentTime = time();
			    $result = pg_query($db,"INSERT INTO anon.posts (poster,title, message, datesubmitted, views, viewcnt) VALUES ('$Name', '$Title', '$Message', $CurrentTime,0,$viewcnt);");
			    if ($result) {
				   $Title = "";
				   $Message = "";
				   $viewcnt = "";
				    echo "Post successfull.<br /><br />";
			    } 
			    else {
			        echo "There was a problem with your post - please try again.<br /><br />";
			    }
			}
			echo '<table style="border:0px solid black;">
				<h2> Post Form</h2>
				<form method="post" action="';echo $return.'">
				Message title: <input type = "text" name="Title" value = "';if(isset($Title)){ echo $Title;} echo'" /><br /><br />
				Message:<BR />
				<textarea name="Message" rows="10" cols="40" >';if(isset($Message)){ echo $Message;} echo'</textarea><br /><br />
				Views until deletion: <input type = "text" name="viewcnt" value = "';if(isset($viewcnt)){ echo $viewcnt;} echo'" /><br /><br />
				Anonymous?<input type="checkbox" name="anonymous"/><br /><br />
				<input type="submit" value="Post" />
				</form>';
			echo '
				<form method="post" action="posts_var.php">
					<input type="hidden" name = "return" value="';echo $return.'" />
					<input type="submit" value="Close Post Form" />
				</form></table></div>
			';
		}
	}
	//echo "HI";
}
?>
