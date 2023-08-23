# Contact Importer: Spring Batch Demonstration

This project showcases the capabilities of Spring Batch to efficiently handle bulk data operations, specifically the import of contacts from a CSV file to a database.

This project is the accompanying source code to a full tutorial on https://tucanoo.com

## Purpose

Spring Batch provides powerful functionalities for processing large volumes of records, including transaction management, chunk processing, and handling of errors. This demonstration imports a sample CSV file containing 100,000 contact records into a database, allowing users to experience and understand the efficiency and flexibility of Spring Batch.

## Prerequisites

- Java 17

## Running the App

Clone and run the app either via your IDE or command line.

## Usage

After the application starts:

1. Trigger the batch job by navigating to the following endpoint in your browser or using an API client (e.g., Postman):

    http://localhost:8080/importExample/start

    This initiates the import process using the sample CSV file `100k_sample_contacts.csv` which can be found under src/main/java/resource

2. To monitor the progress and status of the import job, visit:

    http://localhost:8080/importExample/status

    This endpoint provides details about the current status of the batch job, including the number of records read/written, progress percentage, and more.  You can repeatedly refresh this page to monitor progress until completion.

## Key Implementation Details

- The batch configuration is located in `BatchImportConfigForContacts`. This class defines the reader, writer, and the step to execute the batch job.

- `ContactImportController` offers endpoints to start the batch job and monitor its status.

- The project leverages Spring Batch's `JobLauncher`, `Job`, and `JobExplorer` to control and inspect the batch operation.

## Conclusion

Spring Batch offers a powerful solution for efficiently and reliably processing large datasets. This project provides a tangible example of its capabilities and serves as a reference for developers aiming to integrate similar features into their solutions.

### Reference Documentation

For further reference, please consider the following sections:

* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#using.devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#web)
* [Spring Batch](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/index.html#howto.batch)


### Additional Links

For further assistance with this or Spring Boot development contact:

* [Tucanoo Solutions Ltd](https://tucanoo.com)

