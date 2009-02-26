<?php
require_once "stdlib.php";
$filter = null;
if (isset($_GET["category"])) {
  $filter = $_GET["category"];
}
?>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <title>Webapplikationsudvikling - Referenceløsning opgave 1</title>
  <link href="style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
    <div class="container">
      <div class="header">
        <?php include "header.php"; ?>
      </div>
      <div class="posts">
        <?php
          $article = new Article;
          $article->orderBy('id desc');
          $article->limit(5);
          if ($filter != null) {
            $article->category = str_replace("\%20"," ",$filter);
          }
          $article->find();
          while ($article->fetch()) {
	    $article->display_here();
	  }
        ?>
      </div>
      <div class="menu">
        <?php include "menu.php"; ?>
      </div>
    </div>
  </body>
</html>
