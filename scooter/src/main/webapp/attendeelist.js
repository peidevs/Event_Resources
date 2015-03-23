define(['dojo/_base/declare',
    'scooter/utils'], function (declare, utils) {
    return declare('AttendeeList', null, {

        constructor: function (names) {
            this.init(names);
        },
        getNumNames: function () {
            return this.names.length;
        },

        getName: function (i) {
            return this.names[i];
        },

        getNumLosers: function () {
            return this.losers.length;
        },

        getNumSurvivors: function () {
            return this.survivors.length;
        },

        clear: function () {
            this.losers = [];
            this.survivors = this.names.slice();
        },

        init: function (names) {
            this.names = utils.shuffleNames(names);
            this.losers = [];
            this.survivors = this.names.slice();
        },

        reset: function () {
            this.init(this.names);
        },

        loses: function (name) {
            this.losers.push(name);
            var nameIndex = this.survivors.indexOf(name);
            this.survivors.splice(nameIndex, 1);
        },

        doesWinnerExist: function () {
            return (this.survivors.length == 1);
        },

        isWinner: function (name) {
            return (this.doesWinnerExist()) && (this.survivors.indexOf(name) == 0);
        },

        isLoserThisRound: function (name) {
            var result = false;

            if ((!this.doesWinnerExist()) && (this.losers.indexOf(name) == -1)) {
                var numChances = 4;

                if (utils.oneInNChance(numChances)) {
                    this.loses(name);
                    result = true;
                }
            }

            return result;
        }
    });
});



