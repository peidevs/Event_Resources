#!/bin/bash 

set -e 

echo "generating coverage..."
./gradlew jacocoTestReport

echo "Ready."
