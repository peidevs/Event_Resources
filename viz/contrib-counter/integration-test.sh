#!/bin/bash 

set -e 

echo "building..."
./gradlew installDist

echo "running..."
cp ./resources/test-data.csv input.csv
./staging/bin/contrib-counter input.csv > out.log

echo "diff-ing..."
diff out.log ./resources/test-data.out.log

echo "Ready."
