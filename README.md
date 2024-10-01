# DevOps-DockerExercise

This project aims to create two interworking services (started and stopped together). The purpose of this project is to create the application as well as the Docker files and Docker Compose file for both services.

## Project Structure

- **Service 1:** A Java-based service.
- **Service 2:** A Python-based service.

## Prerequisites

- Docker
- Docker Compose
- Linux (used for development and testing)

## Docker Files

- Dockerfile (Service 1): Contains the instructions to build the Docker image for Service 1.
- Dockerfile (Service 2): Contains the instructions to build the Docker image for Service 2.
- docker-compose.yml: Defines and runs multi-container Docker applications.

## To Run This Project

1. **Clone the repository:**

  ->  git clone https://github.com/AnisulMahmud/DevOps-DockerExercise.git

  -> cd DevOps-DockerExercise

2.  **Build and Start the service with docker Compose:**

  ->  Run this command on the terminal:  sudo docker-compose up --build

  -> You can run this command to terminal to check the output: curl http://localhost:8199

  -> Or you can open your browser and navigate to http://localhost:8199

## Output
  ![image](https://github.com/user-attachments/assets/6bea338f-9ef0-4851-819c-06565cb37511)

