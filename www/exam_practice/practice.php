<html>
<body>
<br>

<?php
echo "1";
?>

<form name="my_input" action="practice.php" method="get">

Text Input: <input type="text" name="myName"><br>
Password: <input type="password" name="myPassword"><br>
<input type="radio" name="handedness" value="myLeft">MyLeft
<input type="radio" name="handedness" value="myRight">MyRight <br>
<input type="checkbox" name="foot" value="myFoot">I have a foot<br>


Username: <input type="text" name="myUsername">
<input type="submit" value="Submit_Me">
</form>

<br>
<br>

<?php>

$a = $_Get["myName"];
$b = $_Get["password"];
$c = $_Get["handedness"];
$d = $_Get["foot"];
$e = $_Get["myUsername"];
echo $a;
echo $b;
echo $c;
echo $d;
echo $e;
?>


</body>
</html>






<!--
Notes on GET:

Appends form-data into the URL in name/value pairs
The length of a URL is limited (about 3000 characters)
Never use GET to send sensitive data! (will be visible in the URL)
Useful for form submissions where a user want to bookmark the result
GET is better for non-secure data, like query strings in Google
GET requests can be cached
GET requests remain in the browser history
GET requests should be used only to retrieve data

Notes on POST:

Appends form-data inside the body of the HTTP request (data is not shown is in URL)
Has no size limitations
Form submissions with POST cannot be bookmarked

-->

