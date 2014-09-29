<html>
<head>
<link type="text/css" rel="stylesheet" href="../chat/cbox.css" />
</head>
<?php
//include_once 'core.php';
//header("Location: chat/write_log.php?text=qq");
?>
<div id="wrapper">
    <div id="menu">
        <p class="welcome">Welcome, <b>
		<?php
		echo $_SESSION['chat']; 
		?></b></p>
        <div style="clear:both"></div>
    </div>
     
    <div id="chatbox">
	<?php
	if(file_exists("../log.html") && filesize("../log.html") > 0){
		$handle = fopen("../log.html", "r");
		$contents = fread($handle, filesize("../log.html"));
		fclose($handle);
		 
		echo $contents;
	}
	?>
	</div>
     
    <form name="message" action="">
        <input name="usermsg" type="text" id="usermsg" size="63" />
        <input name="submitmsg" type="submit"  id="submitmsg" value="Send" />
    </form>
</div>
<script type = "text/javascript" src="chat/jquery.js"></script>
<script type="text/javascript">
// jQuery Document

$("#submitmsg").click(function(){   
    var clientmsg = $("#usermsg").val();
    $.post("chat/write_log.php", {text: clientmsg});         
    $("#usermsg").prop("value", "");
    return false;
});

function loadLog(){     
    var oldscrollHeight = $("#chatbox").prop("scrollHeight") - 20; //Scroll height before the request
    $.ajax({
        url: "../log.html",
        cache: false,
        success: function(html){        
            $("#chatbox").html(html); //Insert chat log into the #chatbox div   
             
            //Auto-scroll           
            var newscrollHeight = $("#chatbox").prop("scrollHeight") - 20; //Scroll height after the request
            if(newscrollHeight > oldscrollHeight){
                $("#chatbox").animate({ scrollTop: newscrollHeight }, 'normal'); //Autoscroll to bottom of div
            }               
        },
    });
}
setInterval (loadLog, 2500); 
</script>
</html>