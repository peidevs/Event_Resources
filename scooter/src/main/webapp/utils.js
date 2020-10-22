/**
 * from http://stackoverflow.com/a/12646864/12704
 * converted to AMD spec
 */
define(function () {
  return {
    shuffleNames: function (array) {
      for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        const temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }

      return array;
    },

    pickOne: function (array) {
      const index = this.getRandom(0, array.length - 1);
      return array[index];
    },

    oneInNChance: function (numChances) {
      const x = this.getRandom(1, numChances);
      const result = x == 1;

      return result;
    },

    getRandom: function (min, max) {
      return Math.floor(Math.random() * (max - min + 1)) + min;
    },
  };
});
