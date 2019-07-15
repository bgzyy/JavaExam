#!/bin/bash
mvn clean compile assembly:single

cd target

ls

java -jar Exam1-1.0-SNAPSHOT-jar-with-dependencies.jar