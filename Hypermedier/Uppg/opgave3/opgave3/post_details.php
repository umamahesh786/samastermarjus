<?php
require_once "stdlib.php";
if (!isset($_GET["id"])) {
  header ("Location:index.php");
  exit;	
}
$id = $_GET["id"];
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Post details</title>
  <link href="style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    <div class="header">
      <?php include "header.php"; ?>
    </div>

    <?php 
      // Fetch the article.
      $article = new Article;
      $article->id = $id;
      $article->find();
      
      // Check if the article exists.
      if ($article->count() == 0) {
	print "<p>The requested article does not exist.</p>";
	print "<p><a href=\"index.php\">back</a> to the frontpage?</p></body><html>";
	exit;
      }
      else {
	// Display the article.
	if ($article->fetch()) {
          $article->display_here();
        }
        else {
	  echo "Error fetching article from database.";
	}
      }
    ?>

    <hr />
  
    <?php
      // Fetch the comments.
      $comment = new Comment;
      $comment->articleid = $id;
      $comment->orderBy('id asc');
      $comment->find();      
   
      // Display any comments.
      if ($comment->count() != 0) {
	while ($comment->fetch()) {
	  $comment->display_here();
	}
      }
      else {
	print "<p>No one has commented on this article yet.</p>";
      }
    ?>
	
    <hr />
	
    <h3>Add comment</h3>
    <form action="post_comment.php" method="post">
      <?php printf ("<input type=\"hidden\" name=\"id\" value=\"%s\" />", $id); ?>
      <table>
        <tr>
          <td>Name:</td>
          <td><input type="text" name="name"  /></td>
        </tr>
        <tr>
          <td>Email address:</td>
          <td><input type="text" name="email" /></td>
        </tr>
        <tr>
          <td>Comment:</td>
          </tr>
      </table>
      <textarea name="comment" rows="10" cols="70"></textarea><br />
      <input type="reset" value="Clear" />
      <input type="submit" value="Post" />
    </form>
  </body>

</html>
