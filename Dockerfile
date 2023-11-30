FROM tomee:9-alpine-plume
#WORKDIR /app
COPY target/jakartaee-hello-world.war /usr/local/tomee/webapps/
#COPY . .
#RUN ./mvnw clean package -f pom.xml

#ENTRYPOINT ["mvn","tomee:run",".f","pom.xml"]