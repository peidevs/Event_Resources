
var NUM_ATTENDEES = 30;

function generateNumericList(n) {
  var list = [];
  
  for (i = 1; i <= n; i++) {
    list.push('' + i);
  }
  
  return list;
}

var ATTENDEE_LIST = generateNumericList(NUM_ATTENDEES);
