@echo OFF
java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5055 .\target\defensive-sql-util-1.0.0.SNAPSHOT-jar-with-dependencies.jar %1 %2 %3