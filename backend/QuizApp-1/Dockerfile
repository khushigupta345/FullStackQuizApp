# Use official OpenJDK 17 image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy source code and pom.xml into the container
COPY . .

# Install Maven (required for building the app)
RUN apt-get update && apt-get install -y maven

# Build the application (without tests to speed up)
RUN mvn clean install -DskipTests

# Run the built jar file
CMD ["java", "-jar", "target/QuizApp-1-0.0.1-SNAPSHOT.jar"]