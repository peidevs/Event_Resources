#!/bin/bash

set -e

./gradlew -q generateDevHtml 

python -m http.server
