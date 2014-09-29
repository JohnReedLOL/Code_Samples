<html>
        <head>
        <title>table.php </title>
        </head>
        <body>

        <?php

        // The value of the variable month is found
        echo "<h1>Month is " . $_GET["month"] . "</h1>";

	$month = $_GET["month"];
         
        // The value of the variable day is found
        echo "<h1>Day is " . $_GET["day"] . " </h1>";

	$day = $_GET["day"];

	$connection = pg_connect(“host=localhost dbname = postgres”);

	$result = pg_query($connection, 'SELECT year, month, day FROM olympics WHERE month = $month and day = $day');
if (!$result) {
  echo "An error occurred.\n";
  exit;}

while ($row = pg_fetch_row($result)) {
  echo "year: $row[0] | month:$row[1] | day: $row[2]";
  echo "<br />\n";
}
        ?>

        </body>
</html>
