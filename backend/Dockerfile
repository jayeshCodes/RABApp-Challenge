# Use the official OpenJDK image for Java and Maven to build your application
FROM maven:3.6.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper and project files
COPY backend/pom.xml ./
COPY backend/src ./src/

# Package the application
RUN mvn clean install -DskipTests

# Use AdoptOpenJDK as the base image
FROM adoptopenjdk/openjdk11:alpine-jre

# Set the working directory in the container
WORKDIR /app

# Copy the packaged JAR file to the container
COPY --from=build /app/target/machadolabtest-0.0.1-SNAPSHOT.jar .

# Define the command to run your application
CMD ["java", "-jar", "machadolabtest-0.0.1-SNAPSHOT.jar"]
