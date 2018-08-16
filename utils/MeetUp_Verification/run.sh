#!/bin/bash 

gradle app:clean app:installDist
./app/staging/bin/app ~/src/github/peidevs/Event_Resources
