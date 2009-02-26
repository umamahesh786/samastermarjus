<?php
require_once "stdlib.php";
// Redirect to the index in case of failure.
if (!isset($_POST["id"])) {
  header ("Location:index.php");
  exit;	
}

// Write the comment to the database.
// Checks to see whether the fields have been properly filled in are
// not done here - they should be done by JavaScripts instead...

// First we find the next id to use.
$comment = new Comment;
$comment->articleid = $_POST["id"];
$comment->id = $comment->count() + 1;

// Then we fill in the rest and insert it into the database.
$comment->author_name = str_replace("\%20"," ", $_POST["name"]);
$comment->author_email = $_POST["email"];
$comment->text = str_replace("\%20"," ", $_POST["comment"]);
$comment->timestamp = time();
$comment->insert();
header("Location:post_details.php?id=" . $_POST["id"]);
exit();
?>