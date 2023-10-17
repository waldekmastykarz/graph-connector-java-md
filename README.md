# Sample connector

## Prerequisites

- Microsoft 365 tenant
- Microsoft Graph PowerShell SDK
- Java 17.0.8
- Gradle 8.4

## Minimal Path to Awesome

```sh
# clone the repo
# create AAD app reg
setup.ps1
# build the project
./gradlew build
# create connection
./gradlew run --args create-connection
# ingest content
./gradlew run --args load-content
```
