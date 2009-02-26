#!/usr/bin/perl

open(FH, "$ARGV[0]");

foreach(<FH>){
	chomp $_;
	print $_ . " ";
}

close(FH);
