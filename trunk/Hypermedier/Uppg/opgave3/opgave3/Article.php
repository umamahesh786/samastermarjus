<?php
/**
 * Table Definition for article
 */
require_once 'DB/DataObject.php';

class Article extends DB_DataObject 
{
    ###START_AUTOCODE
    /* the code below is auto generated do not remove the above tag */

    public $__table = 'article';                         // table name
    public $id;                              // int(11)  not_null primary_key auto_increment
    public $title;                           // string(255)  not_null
    public $category;                        // string(64)  not_null
    public $timestamp;                       // int(11)  not_null
    public $text;                            // blob(65535)  blob

    /* Static get */
    function staticGet($k,$v=NULL) { return DB_DataObject::staticGet('Article',$k,$v); }

    /* the code above is auto generated do not remove the tag below */
    ###END_AUTOCODE

    public function display_here () {
      // Display the title header.
      printf ("<p class=\"postheader\">\n\t<a href=\"post_details.php?id=%d\">%s</a>\n</p>\n", 
	      $this->id, $this->title);
      
      // Display the meta header.
      printf ("<p class=\"postmetaheader\">\n\tPosted in: <a href=\"index.php?category=$this->category\">$this->category</a> on: %s\n</p>\n", date("d/m/Y H:i", $this->timestamp));
      
      // Display the contents of the article.
      printf("<p class=\"postbody\">\n\t%s\n<p>\n",
	     $this->text);
    }
}
