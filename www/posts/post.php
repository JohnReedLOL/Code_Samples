<?php
	require '../dbconnect.php';
	require '../core.php';
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
            echo "Your message has been posted - thanks!<br /><br />";
            echo "<A HREF=\"../index.php\">Back to Home</a>";
            exit;
        } else {
            echo "There was a problem with your post - please try again.<br /><br />";
        }
    }
?>
    
<form method="post" action="post.php">
Message title: <input type = "text" name="Title" value = "<?php if(isset($Title)){ echo $Title;} ?>" /><br /><br />
Message:<BR />
<textarea name="Message" rows="10" cols="40" ><?php if(isset($Message)){ echo $Message;} ?></textarea><br /><br />
Views until deletion (Maximum 100 views): <input type = "text" name="viewcnt" value = "<?php if(isset($viewcnt)){ echo $viewcnt;} ?>" /><br /><br />
Anonymous?<input type="checkbox" name="anonymous"/><br /><br />
<input type="submit" value="Post" />
</form>

<?php 
	chat($here);
?>