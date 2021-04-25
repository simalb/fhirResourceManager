TOMCAT_PATH="/home/guest/apache-tomcat-9.0.45/webapps/"
WAR_PATH="/home/guest/esimalb/accontento/fhirResourceManager/servlets/servlet/target/servlet-1.0.war"


echo "Easy Tomcat Deploy"
echo "Tomcat path: $TOMCAT_PATH"
echo "war path: $WAR_PATH"

cp $WAR_PATH $TOMCAT_PATH
