### Scooter

What it is:
* a simple app to help award door-prizes at events

About the name:
* [Scooter](http://muppet.wikia.com/wiki/Scooter) is a stage assistant on the Muppet Show

Prime directive:
* this app was written in anger to solve a problem
* **all versions must allow someone to award a door-prize by simply munging an attendee list into a text/JS file**
* 10 minutes prep time, max (assuming Gradle is installed and they are familiar with the app)
* auto-curating from MeetUp or EventBrite should be ancillary apps

Requires:
* [Gradle](http://gradle.org)

To Run:
* edit `src/main/webapp/ATTENDEE_LIST_EDIT_THIS_ONE.js`
* **or** edit NAMES.txt and run `gradle generateAttendeeListEditThisOne`
* ./run.sh
* browse to [local Scooter](http://localhost:8787/scooter/index.html)

To Use:
* **click Go** to play a round
* each person has a 1-in-N chance of losing this round
* NOTE: it's possible for no one to lose in a given round
* losers will disappear, ultimately the winner will spin
* **click Reset** to restore names
* **refresh browser** to reshuffle names

Credits:
* inspired by Dojo [CSS3 demo](http://demos.dojotoolkit.org/demos/css3/demo.html)
* original source for CSS3 demo [here](https://github.com/dojo/demos/tree/master/css3)
* uses [Dojo](http://dojotoolkit.org/)
* uses [Jasmine](http://jasmine.github.io/)


