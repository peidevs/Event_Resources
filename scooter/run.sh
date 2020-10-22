#!/bin/bash

set -e 

echo ""
echo ""
echo "http://localhost:8787/scooter/index.html?token=`date +%m%d%H%M%S`"
echo ""
echo ""

./gradlew -q generateDevHtml jettyRun
