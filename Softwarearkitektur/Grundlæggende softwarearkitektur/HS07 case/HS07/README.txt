HS07 Prototype
==============

This is an architectural prototype of the HS07 system for heating control in a home.

To build the files it is assumed that you have a working version of
the "Ant" build tool.

Build options
-------------

Run "ant" in this directory to get a list of build options.

Running HS07
------------

To run HS07 do (from this directory)

1) "ant run-gateway". This starts a gateway
2) "ant run-thermometers" and "ant run-radiators" in other shells. 
   This starts a simulation of the thermometers and radiators

The number of thermometers and radiators started and the URLs of services
are controlled with properties in the build file
  
Questions?
----------
mailto:klaus.m.hansen@daimi.au.dk

