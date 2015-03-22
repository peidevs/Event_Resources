/**
 * from http://stackoverflow.com/a/12646864/12704
 * converted to AMD spec
 */
define(function () {
    return {
        shuffleNames: function (array) {

            for (var i = array.length - 1; i > 0; i--) {
                var j = Math.floor(Math.random() * (i + 1));
                var temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }

            return array;
        },

        pickOne: function (array) {
            var index = this._getRandom(0, array.length - 1);
            return array[index];
        },

        oneInNChance: function (numChances) {
            var result = false;

            var x = this._getRandom(1, numChances);

            if (x == 1) {
                result = true;
            }

            return result;
        },

        _getRandom: function (min, max) {
            var x = Math.floor(Math.random() * (max - min + 1)) + min;
            return x;
        }
    };
});