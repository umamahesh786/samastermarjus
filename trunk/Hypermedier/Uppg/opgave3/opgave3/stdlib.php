<?php
define("ROOT", dirname(__FILE__));

$options = &PEAR::getStaticProperty('DB_DataObject','options');
$options["database"] = "mysql://madsk:MdsKK47@mysql.daimi.au.dk/madsk";
$options["schema_location"] = ROOT;
$options["class_location"]  = ROOT;
$options["require_prefix"]  = "";
$options["class_prefix"]    = "";
$options["generator_include_regex"] = "/^article|^comment/";

function __autoload ($classname) {
  require_once "$classname.php";
}

error_reporting(E_ERROR | E_WARNING | E_PARSE | E_NOTICE);
?>
