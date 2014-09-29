<br><table border=1 cellpadding = 5><tr><th><u>Follower Info:</u></th></tr>
<tr><td>
<?php
    include 'dbconnect.php';
	include_once 'core.php';
	
	$origin = $_SESSION['friend_origin'];
	if($_SESSION['friends_var'] == 1){
		$Name = $_SESSION['user_name'];
		$result = pg_query($db,"SELECT * FROM anon.friends WHERE friend_1 = '$Name';");
		if(isset($_POST['remove'])){
			$friend = $_POST['remove'];
			$result2 = pg_query($db,"DELETE FROM anon.friends WHERE friend_1 = '$Name' AND friend_2 = '$friend';");
			header("Location: $origin");
		}
		if (!pg_num_rows($result)) {
			echo "<br>Start following people!<br><br>";
		} 
		else {
			echo "<br><table border=1 cellpadding = 5><tr><th><u>Currently Following:</u></th></tr>";
			while ($row = pg_fetch_array($result,NULL,PGSQL_ASSOC)) {
				echo "<tr><td>$row[friend_2]</td>
					<td><form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'remove' value='";echo $row['friend_2']."' />
					<input type='submit' value='Remove' />
					</form></td></tr>";
			}
			echo "</table><br>";
		}
		$result = pg_query($db,"SELECT COUNT(following) as follower_count FROM (SELECT friend_1 as following FROM anon.friends WHERE friend_2 = '$Name')as followers;");
		if (!pg_num_rows($result)) {
			echo "You have 0 followers.";
		} 
		else{
			$row = pg_fetch_array($result,NULL,PGSQL_ASSOC);
			echo "<table border=1 cellpadding = 5><tr><th><u>Followers:</u></th><td>You have $row[follower_count] followers.</td></tr></table>";
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
			if (isset($_POST['friend'])) {
				$Name = $_SESSION['user_name'];
				$Fname = $_POST['friend'];
				//You are friend_1
				//You are following friend_2
				$result = pg_query($db,"SELECT * FROM anon.friends WHERE friend_1 = '$Name' AND friend_2 = '$Fname';");
				if(pg_num_rows($result)){
					echo "You are already following this person.";
				}
				else if($Name == $Fname){
					echo "Nice try.";
				}
				else{
					$result = pg_query($db,"SELECT * FROM anon.users WHERE username = '$Fname';");
					if(!pg_num_rows($result)){
						echo "This user does not exist.";
					}
					else{
						$result = pg_query($db,"INSERT INTO anon.friends (friend_1, friend_2) VALUES ('$Name', '$Fname');");
						if ($result) {
							header("Location: $origin");
						} else {
							echo "There was a problem with your request - please try again.<br /><br />";
						}
					}
				} 
			}
			echo "
				<br><table border= solid cellpadding = 5>
				<tr>
				<td>
				<form method='post' action='"; echo $origin."'>
				Who do you want to follow?<input type='text' name='friend'><br>
				<input type='submit' value='Submit' />
				</form>
				</td>
				</tr>
				</table>
			";
			echo "
				<form method='post' action='"; echo $origin."'>
					<input type='hidden' name = 'return1' value='reset' />
					<input type='submit' value='Close' />
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
					<input type='submit' value='Become a Follower' />
				</form>
			";
		}
	}
?>
</tr></td>
</table><br>