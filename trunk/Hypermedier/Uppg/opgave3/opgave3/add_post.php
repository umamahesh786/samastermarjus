<?php 
require_once "stdlib.php";
if (isset($_POST["title"]) and $_POST["title"] != "") {
  // Insert the article into the database.
  // There are no checks here to see whether the fields have valid 
  // content - this should be placed in some JavaScript instead.
  $article = new Article;
  $article->timestamp = time();
  $article->title = str_replace("\%20"," ",$_POST["title"]);
  $article->text = str_replace("\%20"," ",$_POST["postbody"]);
  $article->category = str_replace("\%20"," ",$_POST["category"]);
  $article->insert();
  header("Location:index.php");
  exit();
}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Add post</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<?php include "header.php"; ?>
	</div>
	<form method="post" action="add_post.php">
		<table>
			<tr>
				<td>Title:</td>
				<td><input type="text" name="title" /></td>
			</tr>
			<tr>
				<td>Categories:</td>
				<td>
					Fun stuff: <input type="radio" name="category" value="Fun stuff" />
					Serious stuff: <input type="radio" name="category" value="Serious stuff" />
					Other stuff: <input type="radio" name="category" value="Other stuff" />
				</td>
			</tr>
			<tr>
				<td>Post body:</td>
			</tr>
		</table>
		<textarea name="postbody" rows="15" cols="70"></textarea><br />
		<input type="reset" value="Clear" />
		<input type="submit" value="Post" />
	</form>
</body>
</html>
