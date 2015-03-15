
var AttendeeList = function(names) {
    this.utils = new Utils();
    this.names = this.utils.shuffleNames(names);
    this.losers = [];
    this.survivors = this.names.slice();
};

AttendeeList.prototype.getNumNames = function() { 
    return this.names.length; 
}

AttendeeList.prototype.getName = function(i) {
    return this.names[i];
}

AttendeeList.prototype.reset = function() {
    this.names = this.utils.shuffleNames(this.names);
    this.losers = [];
    this.survivors = this.names.slice();
}

AttendeeList.prototype.loses = function(name) {
    this.losers.push(name);
    var nameIndex = this.survivors.indexOf(name);
    this.survivors.splice(nameIndex, 1);                
}

AttendeeList.prototype.doesWinnerExist = function() {
    return (this.survivors.length == 1);
}

AttendeeList.prototype.isWinner = function(name) {
    return (this.doesWinnerExist()) && (this.survivors.indexOf(name) == 0);
}

AttendeeList.prototype.isLoserThisRound = function(name) {
    var result = false;
    
    if ((! this.doesWinnerExist()) && (this.losers.indexOf(name) == -1)) {
        var chanceFactor = 4;
        var x = this.utils.getRandom(1,chanceFactor);
        
        if (x == 1) {
            this.loses(name);
            result = true;
        }            
    }
    
    return result;
}
