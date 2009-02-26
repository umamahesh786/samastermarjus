<html>
	<head>
		<title>Dojo test1</title>

		<script type="text/javascript" src="../../dojo.js"></script>
		<script type="text/javascript">
			dojo.require("dojo.io.bind");
		</script>

		<script type="text/javascript">
			window.onload = function() {
			
				// simple load
				dojo.io.bind({
				    url: "sampleData.cfm",
				    load: function(type, data, evt){ document.getElementById('ajax_div').innerHTML = data; },
				    error: function(type, error){ /*do something w/ the error*/ },
				    mimetype: "text/plain"
				});
			}
						
		</script>

	</head>
	<body>
	
		<div id="ajax_div"></div>

	</body>
</html>