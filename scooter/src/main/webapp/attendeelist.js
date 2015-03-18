
var AttendeeList = function(names) {
    this.utils = new Utils();
    this.init(names);
};

AttendeeList.prototype.getNumNames = function() { 
    return this.names.length; 
}

AttendeeList.prototype.getName = function(i) {
    return this.names[i];
}

AttendeeList.prototype.getNumLosers = function() { 
    return this.losers.length; 
}

AttendeeList.prototype.getNumSurvivors = function() { 
    return this.survivors.length; 
}

AttendeeList.prototype.clear = function() {
    this.losers = [];
    this.survivors = this.names.slice();
}

AttendeeList.prototype.init = function(names) {
    this.names = this.utils.shuffleNames(names);
    this.losers = [];
    this.survivors = this.names.slice();
}

AttendeeList.prototype.reset = function() {
    this.init(this.names);
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
        var numChances = 4;
        
        if (this.utils.oneInNChance(numChances)) {
            this.loses(name);
            result = true;
        }            
    }
    
    return result;
}
