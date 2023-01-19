#!/bin/bash 

set -e 

echo "building..."
./gradlew installDist

echo "running..."
cp ../../MeetUps_v2.csv input.csv
./staging/bin/counter input.csv > out.log

cat out.log

echo "Ready."
