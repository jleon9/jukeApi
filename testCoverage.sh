#!/bin/bash
mvn clean verify
cd target/site/jacoco
./index.html
cd ../../..
