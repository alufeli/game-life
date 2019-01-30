#!/bin/sh
cd /home/wwb/BusinessSupervise/

appJarName='Business-1.5.6.RELEASE'

java -Dloader.path=./lib -Xms256m -Xmx1024m -jar ./lib/$appJarName.jar -Dspring.profiles.active=prod  > /dev/null &

echo $!>bin/ms.pid

echo "BusinessSupervise process is running, see details in logs directory"
~