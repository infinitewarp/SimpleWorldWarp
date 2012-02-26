SimpleWorldWarp
===============

Simple bukkit plugin for warping between worlds.

Installation
------------

1. Drop SimpleWorldWarp.jar into the bukkit plugins folder.
2. Reload bukkit plugins.
3. There is no step 3!

Configuration
-------------

SimpleWorldWarp is simple! It simply works with the worlds running on your bukkit server. If you add new worlds, SimpleWorldWarp automatically maintains its own config file to track and reload those new worlds upon server restart.

Permissions
-----------

SimpleWorldWarp supports the following bukkit permissions nodes:

* simpleworldwarp.*
* simpleworldwarp.wcreate
* simpleworldwarp.wdelete
* simpleworldwarp.wwarp
* simpleworldwarp.wlist

By default, all SimpleWorldWarp commands are disabled for all users.

Version History
---------------

* *1.0.3*: Fix bug where creating new world saved incorrect environment type.
* *1.0.2*: Remove unnecessary logging. Consistently save loaded world metadata upon plugin reload.
* *1.0.1*: Maintain world metadata across server restarts.
* *1.0*: Initial release!