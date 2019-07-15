#!/bin/bash
mvn clean compile assembly:single

cd target

ls

java -jar Exam2-jar-with-dependencies.jar