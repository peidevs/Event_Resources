#!/bin/bash

set -e

./gradlew generateDevHtml 

echo ""
echo "browse to: http://localhost:8000/test/SpecRunner.html"
echo ""
echo ""

python -m http.server
