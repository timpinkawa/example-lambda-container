# example-lambda-container

A simple example of using [Jib](https://github.com/GoogleContainerTools/jib) to build a Lambda container for local testing with no Dockerfile.

## Building the container
1. Ensure that a Docker daemon is running
2. `./gradlew jibDockerBuild`

This will create an image named `example-lambda-handler:latest`.

## Starting the container

### Command line
`docker run -p 9000:8080 -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005" example-lambda-handler:latest`

### Compose
`docker compose up`

## Invoking the Lambda
`curl http://localhost:9000/2015-03-31/functions/function/invocations -d @input.json`
