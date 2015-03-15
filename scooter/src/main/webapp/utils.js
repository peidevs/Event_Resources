var Utils = function() {}

// from http://stackoverflow.com/a/12646864/12704
Utils.prototype.shuffleNames = function(array) {
    
    for (var i = array.length - 1; i > 0; i--) {
        var j = Math.floor(Math.random() * (i + 1));
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    return array;
}

Utils.prototype.getRandom = function(min, max) {
    var x = Math.floor(Math.random() * (max - min + 1)) + min;
    return x;
}
