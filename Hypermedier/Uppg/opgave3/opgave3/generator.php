<?php

require_once('stdlib.php');

function regenerateDataObjects() {
  require_once 'DB/DataObject/Generator.php';
  $generator = new DB_DataObject_Generator;
  $generator->start();
}

regenerateDataObjects();
?>

