<?php
	include_once '../dbconnect.php';
	include_once '../core.php';
	
	if (isset($_POST['cancel']) && isset($_SESSION['pid'])) {
		if(!$_SESSION['pid']){
			echo "$error";
		}
		else{
			$pid = $_SESSION['pid'];
			$_SESSION['comment'] = 0;
			$_SESSION['pid'] = 0;
			header("Location: read.php?MBID=$pid");
		}
	}
    if (isset($_POST['Message']) && isset($_SESSION['pid'])) {
		if(!$_SESSION['pid']){
			echo "$error";
		}
		else{
			if(isset($_POST['anonymous'])){
				$Name = "anonymous";
			}
			else{
				$Name = $_SESSION['user_name'];
			}
			$pid = $_SESSION['pid'];
			$Message = addslashes($_POST['Message']);
			$CurrentTime = time();
			$result = pg_query($db,"INSERT INTO anon.comments (pid,poster, message, datesubmitted) VALUES ('$pid','$Name', '$Message', $CurrentTime);");
			if ($result) {
				$_SESSION['comment'] = 0;
				$_SESSION['pid'] = 0;
				header("Location: read.php?MBID=$pid");
			} else {
				echo "There was a problem with your post - please try again.<br /><br />";
			}
		}
        
    }
	//#comments {border:solid 1px black; height = 10px; width = 500px; padding:10px;}
?>
<style>
</style>
<div id = "comments">   
<table border= solid cellpadding = 10>
<tr>
<td>
<form method="post" action="comment_form.php">
Message:<BR />
<textarea name="Message" rows="10" cols="40" ><?php if(isset($Message)){ echo $Message;} ?></textarea><br /><br />
Anonymous?<input type="checkbox" name="anonymous"/><br /><br />
Cancel<input type="checkbox" name="cancel"/><br /><br />
<input type="submit" value="Submit" />
</form>
</td>
</tr>
</table>
</div>