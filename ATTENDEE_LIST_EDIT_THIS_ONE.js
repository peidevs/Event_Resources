// auto-generated from NAMES.txt

function generateNumericList(n) {
  var list = [];
  
  for (i = 1; i <= n; i++) {
    list.push('' + i);
  }
  
  return list;
}

var NUM_ATTENDEES = 30;
var ATTENDEE_LIST = generateNumericList(NUM_ATTENDEES);
