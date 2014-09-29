<br><table border=1 cellpadding = 5><tr><th><u>Friends List:</u></th></tr>
<tr><td>
<?php
    include 'dbconnect.php';
	include_once 'core.php';
	
	$origin = $_SESSION['friend_origin'];
	if($_SESSION['friends_var'] == 1){
		$Name = $_SESSION['user_name'];
		$result = pg_query($db,"SELECT * FROM anon.friends WHERE friend_1 = '$Name' OR friend_2 = '$Name';");
		if (!pg_num_rows($result)) {
			echo "<br>Go make some friends...OR continue being truely anonymous<br>";
		} 
		else {
			echo "<br><table border=1 cellpadding = 5><tr><th><u>Your friends:</u></th></tr>";
			while ($row = pg_fetch_array($result,NULL,PGSQL_ASSOC)) {
				if($row['friend_1'] == $Name){
					echo "<tr><td>$row[friend_2]</td></tr>";
				}
				else{
					echo "<tr><td>$row[friend_1]</td></tr>";
				}
			}
			echo "</table><br>";
		}
?>
</tr></td>
<tr><td>
<?php
		if($_SESSION['f_request'] == 1){
			if (isset($_POST['return1']) && $_POST['return1'] == 'reset') {
				$_SESSION['f_request'] = 0;
				header("Location: $origin");
			}
			if (isset($_POST['Message']) && isset($_POST['friend'])) {
				$Name = $_SESSION['user_name'];
				$Message = addslashes($_POST['Message']);
				$Fname = $_POST['friend'];
				$result = pg_query($db,"SELECT * FROM anon.friends WHERE (friend_1 = '$Name' AND friend_2 = '$Fname') OR (friend_2 = '$Name' AND friend_1 = '$Fname');");
				if(pg_num_rows($result)){
					echo "You are already friends with this person.";
				}
				else{
					$result = pg_query($db,"INSERT INTO anon.friend_req (name,fname, message) VALUES ('$Name', '$Fname', '$Message');");
					if ($result) {
						echo "Your request was sent.<br /><br />";
					} else {
						echo "There was a problem with your request - please try again.<br /><br />";
					}
				} 
			}
			echo "
				<table border= solid cellpadding = 5>
				<tr>
				<td>
				<form method='post' action='"; echo $origin."'>
				Your Friend's Username:<input type='text' name='friend'><br>
				Message:<BR />
				<textarea name='Message' rows='10' cols='40' ></textarea><br /><br />
				<input type='submit' value='Submit' />
				</form>
				</td>
				</tr>
				</table>
			";
			echo "
				<form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'return1' value='reset' />
					<input type='submit' value='Close Request Form' />
				</form>
			";
		}
		else{
			if (isset($_POST['return1']) && $_POST['return1'] == 'set') {
				$_SESSION['f_request'] = 1;
				header("Location: $origin");
			}
			echo "
				<form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'return1' value='set' />
					<input type='submit' value='Add a Friend' />
				</form>
			";
		}
?>
</tr></td>
<tr><td>
<?php
		if($_SESSION['view_request'] == 1){
			if (isset($_POST['return2']) && $_POST['return2'] == 'reset') {
				$_SESSION['view_request'] = 0;
				header("Location: $origin");
			}
			$Name = $_SESSION['user_name'];
			$result = pg_query($db,"SELECT * FROM anon.friend_req WHERE fname = '$Name';");
			if (!pg_num_rows($result)) {
					echo "<br>There are no requests at this time.<br>";
				} 
			else {
				echo "<br><table border=1 cellpadding = 5><tr><th><u>Current Requests:</u></th></tr>";
				while ($row = pg_fetch_array($result,NULL,PGSQL_ASSOC)) {
					echo "<tr><td>Name of friend: $row[name].<br />";
					echo "Message: $row[message]</td>
					<td><form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'friend' value='";echo $row['name']."' />
					<input type='hidden' name='choice' value='accept' />
					<input type='submit' value='Accept' />
					</form></td>
					<td><form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'friend' value='";echo $row['name']."' />
					<input type='hidden' name='choice' value='decline' />
					<input type='submit' value='Decline' />
					</form></td></tr>";
				}
				echo "</table><br>";
			}
			if(isset($_POST['choice']) && isset($_POST['friend'])){
				$friend = $_POST['friend'];
				if($_POST['choice'] == 'accept'){
					$result = pg_query($db,"INSERT INTO anon.friends (friend_1, friend_2) VALUES ('$Name', '$friend');");
					$result = pg_query($db,"DELETE FROM anon.friend_req WHERE fname = '$Name' AND name = '$friend';");
					header("Location: $origin");
				}
				else{
					$result = pg_query($db,"DELETE FROM anon.friend_req WHERE fname = '$Name' AND name = '$friend';");
					header("Location: $origin");
				}
			}
			echo "
				<form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'return2' value='reset' />
					<input type='submit' value='Close Request List' />
				</form>
			";
		}
		else{
			if (isset($_POST['return2']) && $_POST['return2'] == 'set') {
				$_SESSION['view_request'] = 1;
				header("Location: $origin");
			}
			echo "
				<form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'return2' value='set' />
					<input type='submit' value='View Requests' />
				</form>
			";
		}
	}
?>
</tr></td>
</table><br>