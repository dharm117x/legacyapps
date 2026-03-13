mvn archetype:generate -DarchetypeGroupId=net.ltgt.gwt.archetypes -DarchetypeVersion=LATEST -DarchetypeArtifactId=modular-webapp

groupId: com.example.gwt
artifactId: gwtmwebapp
version: HEAD-SNAPSHOT
package: com.example.gwt
module-short-name: gwtmwebapp

D:\projects\legacyapps\gwtmwebapp>

mvn gwt:codeserver -pl *-client -am

mvn jetty:run -pl *-server -am -Denv=dev -Djetty.port=9080
or no debug mode:
mvn jetty:run-war -pl *-server -am -Djetty.port=9080

Debug in chrome brwoser source and selct class to debug with break point.


http://localhost:8080
