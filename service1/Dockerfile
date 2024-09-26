FROM openjdk:17-jdk-alpine

# Install bash
# RUN apk add --no-cache bash

# Set the working directory
WORKDIR /app

# Copy the current directory contents intot the container at /app
COPY src/ /app/src/

# Compile the java program
RUN javac src/Main.java

# # Run the Java application
# CMD [ "java", "Main" ]

# Expose the port your application runs on
EXPOSE 8199

# Specify the entry point to run the compiled java class
ENTRYPOINT [ "java", "-cp", "src", "Main" ]
