FROM tomcat:9.0.14-jre8-alpine
ADD fhirResourceManager-servlet/target/fhirResourceManager-servlet.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]