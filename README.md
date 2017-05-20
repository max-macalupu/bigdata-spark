# bigdata-spark

Este repositorio ejecuta el clasico Hello World con apache Spark en windows para ello necesitan descargar esta dependencia de apache hadoop

"http://public-repo-1.hortonworks.com/hdp-win-alpha/winutils.exe" y pegarla dentro de alguna carpeta {path}/bin/winutils.exe

para poder ejecutar solo se necesita descargar el proyecto y desde el gitbash ejecutar los siguientes pasos

1.- mvn clean install
2.- cd target
3.- java -jar HelloWorldSparkJava-1.0-SNAPSHOT.jar {path}
