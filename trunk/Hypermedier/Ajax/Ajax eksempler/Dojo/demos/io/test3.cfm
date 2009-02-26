<html>
	<head>
		<title>Dojo test3</title>

		<script type="text/javascript" src="../../dojo.js"></script>
		<script type="text/javascript">
			dojo.require("dojo.io.bind");
		</script>

		<script type="text/javascript">
			sendForm = function() {
			
			// the back button
			var sampleFormNode = document.getElementById("frm1");
			
			dojo.io.bind({
			    url: "echo.cfm",
			    load: function(type, evaldObj){ 
			        // hide the form when we hear back that it submitted successfully
			        document.getElementById('ajax_div').innerHTML = evaldObj;
			        sampleFormNode.style.display = "none";
			    },
			    backButton: function(){ 
			        // ...and then when the user hits "back", re-show the form
			        document.getElementById('ajax_div').style.display = "none";
			        sampleFormNode.style.display = "";
			    },
			    forwardButton: function(){ 
			        // and if they hit "forward" before making another request, this
			        // happens:
			        document.getElementById('ajax_div').style.display = "";
			        sampleFormNode.style.display = "none"; // we don't re-submit
			    },
			    formNode: sampleFormNode
			});
			return false;
			}
						
		</script>

	</head>
	<body>
		<form id="frm1" onsubmit="return sendForm();">
			FNAME: <input type="text" name="fname" value="" /><br />
			LNAME: <input type="text" name="lname" value="" /><br />
			UNAME: <input type="text" name="uname" value="" /><br />
			Gender: <input type="radio" name="gender" value="male" checked="true"> male
			<input type="radio" name="gender" value="female"> female <br />
			Age: <select name="age">
				<option value="10 to 50">10 to 50</option>
				<option value="50+">50+</option>
			</select><br />
			<input type="submit"  value="send">
		</form>
		<div id="ajax_div"></div>

	</body>
</html>