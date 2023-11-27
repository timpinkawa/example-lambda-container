plugins {
    id("java")
    id("com.google.cloud.tools.jib") version "3.4.0"
}

group = "net.timpinkawa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.amazonaws:aws-lambda-java-core:1.2.3")
    // Common AWS events
    implementation("com.amazonaws:aws-lambda-java-events:3.11.3")
    // Used to provide the entry point to launch the handler
    implementation("com.amazonaws:aws-lambda-java-runtime-interface-client:2.4.1")
}

jib {
    container {
        entrypoint = listOf("/usr/local/bin/aws-lambda-rie")
        args = listOf("/var/lang/bin/java",
            "-cp", "/var/runtime/lib/*:/app/libs/*:/app/classes:/app/resources",
            "com.amazonaws.services.lambda.runtime.api.client.AWSLambda",
            "net.timpinkawa.ExampleHandler::handleRequest")
    }
    from {
        image = "public.ecr.aws/lambda/java:21"
    }
    to {
        image = "example-lambda-handler:latest"
    }
}
