<html>
        <head>
        <title>Form</title>
        </head>
        <body>

        <h1>Enter a text description</h1>

        <form method="post" action="upload.php">
        <input type="text" name="username">
        <input type="submit">
        </form>

<h2>Enter a file</h2>


<form action="upload.php" method="post"
enctype="multipart/form-data">
<label for="file">Filename:</label>
<input type="file" name="file" id="file"><br>
<input type="submit" name="submit" value="Submit">
</form>


        </body>
</html>
