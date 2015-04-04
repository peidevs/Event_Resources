define(['scooter/utils'], function (utils) {
    describe("utils", function () {

        describe("random", function () {
            it("should be able to generate random number in range", function () {
                var result = utils.getRandom(1, 10);

                expect(result).toBeGreaterThan(0);
                expect(result).toBeLessThan(11);
            });

            it("should be able to pick random item in list", function () {
                var list = ["a", "b", "c"];
                var countA = 0;
                var countB = 0;
                var countC = 0;

                for (var i = 1; i <= 500; i++) {
                    var result = utils.pickOne(list);

                    if (result == "a") {
                        countA++;
                    } else if (result == "b") {
                        countB++;
                    } else if (result == "c") {
                        countC++;
                    }
                }

                expect(countA).toBeGreaterThan(0);
                expect(countB).toBeGreaterThan(0);
                expect(countC).toBeGreaterThan(0);
            });

            it("should be able to roll a dice", function () {
                var countA = 0;
                var countB = 0;
                var n = 4;

                for (var i = 1; i <= 500; i++) {
                    var result = utils.oneInNChance(n);

                    if (result) {
                        countA++;
                    } else {
                        countB++;
                    }
                }

                expect(countA).toBeGreaterThan(0);
                expect(countB).toBeGreaterThan(0);
            });

        });

        describe("shuffle", function () {
            it("should be able to shuffle an array", function () {
                var list = [1, 2, 3, 4, 5];

                var result = utils.shuffleNames(list);

                expect(result.length).toEqual(5);

                for (var i = 1; i <= 5; i++) {
                    expect(result).toContain(i);
                }
            });
        });
    });
});

