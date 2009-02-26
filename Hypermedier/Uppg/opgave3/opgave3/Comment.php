<?php
/**
 * Table Definition for comment
 */
require_once 'DB/DataObject.php';

class Comment extends DB_DataObject 
{
    ###START_AUTOCODE
    /* the code below is auto generated do not remove the above tag */

    public $__table = 'comment';                         // table name
    public $id;                              // int(11)  not_null primary_key
    public $articleid;                       // int(11)  not_null multiple_key
    public $author_name;                     // string(128)  not_null
    public $author_email;                    // string(128)  
    public $timestamp;                       // int(11)  not_null
    public $text;                            // blob(65535)  blob

    /* Static get */
    function staticGet($k,$v=NULL) { return DB_DataObject::staticGet('Comment',$k,$v); }

    /* the code above is auto generated do not remove the tag below */
    ###END_AUTOCODE

    public function display_here () {
      printf ("<p class=\"commentheader\">Comment from: <a href=\"mailto:%s\">%s</a>, on: %s</p>",
	      $this->author_email, $this->author_name, date("d/m/Y H:i", $this->timestamp));
      printf ("<p class=\"commentbody\">%s</p>", $this->text);
    }
}
