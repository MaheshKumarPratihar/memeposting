# FROM amazoncorretto:21-alpine
# ARG JAR_FILE=target/*.jar
# COPY ${JAR_FILE} app.jar

# EXPOSE 8081
# ENTRYPOINT ["java","-jar","/app.jar"]


# Use a base image with JDK 21
FROM amazoncorretto:21-alpine as build

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a minimal base image for running the application
FROM amazoncorretto:21-alpine

# Set the working directory
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8081

# Set environment variable for MongoDB URI
ENV SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/memes

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]