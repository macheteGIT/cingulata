<h1>Cingulata</h1> 
Travis-CI [![Build Status](https://travis-ci.org/BattleBeaver/cingulata.svg?branch=master)](https://travis-ci.org/BattleBeaver/cingulata)

Codacy [![Codacy Badge](https://api.codacy.com/project/badge/grade/0a58a39185b94db49c0c9bdb8017c977)](https://www.codacy.com/app/kuzmentsov/cingulata)

Test Servers

https://demo.cingulata.org
http://www.cingulata.org

Installing on local machine

Java 8, Scala 2.11, sbt 0.13 - required

cd into root directory and type <b>sbt run</b>


<h1>Apache2 Configuration Sample</h1>

LoadModule proxy_module modules/mod_proxy.so
<pre>
&lt;VirtualHost *:80/&gt;
  ProxyPreserveHost On
  ServerName admin.cingulata.org
  ProxyPass  /excluded !
  ProxyPass / http://127.0.0.1:9000/admin/categories
  ProxyPassReverse / http://127.0.0.1:9000/categories
&lt;/VirtualHost/&gt;
&lt;VirtualHost *:80/&gt;
  ProxyPreserveHost On
  ServerName demo.cingulata.org
  ProxyPass  /excluded !
  ProxyPass / http://127.0.0.1:9000/
  ProxyPassReverse / http://127.0.0.1:9000/
&lt;/VirtualHost/&gt;
</pre>
