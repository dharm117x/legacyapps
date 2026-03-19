software requirements:
- Java 8
- Maven 3.6.3 or higher
- GWT 2.8.2 or higher
- jetty 9.4.31.v20200723

mvn clean install
mvn clean gwt:compile // file src/main/java/com/example/App.gwt.xml must exist not in resource folder but in java folder
or
mvn gwt:compile -Dgwt.module=com.example.App

mvn gwt:run-codeserver -Dgwt.compiler.strict=true

>mvn jetty:run-war -Djetty.port=9080

mvn gwt:run-codeserver
mvn gwt:run-codeserver -Dgwt.logLevel=DEBUG
mvn gwt:run-codeserver -Dgwt.logLevel=TRACE
