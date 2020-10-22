#!/bin/bash

set -e

./gradlew generateDevHtml 

python -m http.server
