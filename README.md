# Quarkus Google Calendar Project

## Overview

This project is a comprehensive study on developing web services using the Quarkus framework and its libraries. The primary focus is on integrating with Google Calendar and, additionally, supporting AWS Lambda functions developed in Node.js. The integration with Google Calendar is particularly highlighted, showcasing the seamless integration with Google services.

## Features

- Quarkus framework for efficient and lightweight Java application development.
- Integration with Google Calendar API for robust calendar functionality.
- AWS Lambda support with Node.js for serverless architecture.

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven
- Quarkus Development Environment
- Google Cloud API Credentials
- AWS Account and AWS CLI for Lambda deployment

### Setup

1. Clone this repository:

   ```bash
   git clone https://github.com/math-ferreira/calendar-alexa-quarkus
   ```

2. Configure Google Calendar API:

   - Obtain API credentials from the [Google Cloud Console](https://console.cloud.google.com/).

3. Configure AWS Lambda to integrate with this backend service:

   - Follow the instructions in the [serverless-spring-awslambda](https://github.com/math-ferreira/serverless-spring-awslambda) repository and run the projects together.

4. Build and Run the Project:

   ```bash
   cd calendar-alexa-quarkus
   mvn clean package
   java -jar target/calendar-alexa-quarkus-1.0.0-SNAPSHOT-runner.jar
   ```

5. Access the application at `http://localhost:8080`.

## Google Calendar Integration

The project demonstrates how to integrate with Google Calendar. Key steps include:

1. Setting up Google Calendar API credentials.
2. Implementing authentication and authorization.
3. Accessing and manipulating calendar events programmatically.

## AWS Lambda Integration

This project seamlessly integrates with AWS Lambda functions developed in Node.js. For detailed deployment instructions, refer to the [serverless-spring-awslambda](https://github.com/math-ferreira/serverless-spring-awslambda) repository.

## Additional Resources

- Quarkus Documentation: [https://quarkus.io/](https://quarkus.io/)
- Google Calendar API Documentation: [https://developers.google.com/calendar](https://developers.google.com/calendar)
- AWS Lambda Documentation: [https://docs.aws.amazon.com/lambda](https://docs.aws.amazon.com/lambda)

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your enhancements.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
