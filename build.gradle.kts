plugins {
    id("java")
    alias(libs.plugins.jib)
}

group = "net.timpinkawa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.aws.lambda.java.core)
    // Common AWS events
    implementation(libs.aws.lambda.java.events)
    // Used to provide the entry point to launch the handler
    implementation(libs.aws.lambda.java.runtimeinterfaceclient)
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
