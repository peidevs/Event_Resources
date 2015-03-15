describe("utils", function() {
    var utils;

    beforeEach(function() {
        utils = new Utils();
    });

    describe( "random", function() {
        it("should be able to generate random number in range", function() {
            var result = utils.getRandom(1,10);

            expect(result).toBeGreaterThan(0);
            expect(result).toBeLessThan(11);
        });        
    });

    describe( "shuffle", function() {
        it("should be able to shuffle an array", function() {
            var list = [1,2,3,4,5];

            var result = utils.shuffle(list);

            expect(result.length).toEqual(5);

            for (var i = 0; i <= 5; i++) {
            expect(result.length).toContain(i);
            }
        });        
    });
});