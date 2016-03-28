<h1>Cingulata - UI</h1> 
<b>Travis-CI :</b> </br>
[![Build Status](https://travis-ci.org/BattleBeaver/cingulata.svg?branch=master)](https://travis-ci.org/BattleBeaver/cingulata)

<b>Codacy :</b> </br>
[![Codacy Badge](https://api.codacy.com/project/badge/grade/0a58a39185b94db49c0c9bdb8017c977)](https://www.codacy.com/app/kuzmentsov/cingulata)

<h1>Test Servers</h1>

Main:
<pre>
    http://demo.cingulata.org
    http://www.cingulata.org
</pre>
Admin pane:

<pre>
    http://admin.cingulata.org
</pre>

<h1>Installing on local machine</h1>

Java 8, Scala 2.11, sbt 0.13 - required

cd into root directory and type :
<pre>sbt run</pre> in console.


<h1>Apache2 Configuration Sample</h1>

Install <b>proxy_http</b> with command:
<pre>a2enmod proxy_http</pre>

Add this line to etc/apache2/apache2.conf
<pre>LoadModule proxy_module modules/mod_proxy.so</pre>

Insert nodes definitions:
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
