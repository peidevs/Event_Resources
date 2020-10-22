define(["scooter/AttendeeList"], function (AttendeeList) {
  describe("attendeelist", function () {
    let attendeeList;
    let attendees;

    beforeEach(function () {
      attendees = ["Bach", "Beethoven", "Chopin", "Liszt", "Mozart"];
      attendeeList = new AttendeeList(attendees);
    });

    describe("lose", function () {
      it("can tell when a player loses", function () {
        attendeeList.loses("Chopin");

        expect(attendeeList.getNumLosers()).toEqual(1);
        expect(attendeeList.getNumSurvivors()).toEqual(4);
      });
    });

    describe("pick a loser", function () {
      it("can adjust when a loser occurs", function () {
        // TODO: pass in a mock so this isn't left to chance
        const result = attendeeList.isLoserThisRound("Chopin");

        if (result) {
          expect(attendeeList.getNumLosers()).toEqual(1);
          expect(attendeeList.getNumSurvivors()).toEqual(4);
        } else {
          expect(attendeeList.getNumLosers()).toEqual(0);
          expect(attendeeList.getNumSurvivors()).toEqual(5);
        }
      });

      it("will not pick a loser when one survivor left", function () {
        attendeeList.loses("Chopin");
        attendeeList.loses("Mozart");
        attendeeList.loses("Beethoven");
        attendeeList.loses("Liszt");

        const result = attendeeList.isLoserThisRound("Chopin");

        expect(result).toEqual(false);
      });
    });

    describe("win", function () {
      it("can tell when someone has won", function () {
        attendeeList.loses("Chopin");
        attendeeList.loses("Mozart");
        attendeeList.loses("Beethoven");
        attendeeList.loses("Liszt");

        expect(attendeeList.doesWinnerExist()).toEqual(true);
        expect(attendeeList.getNumSurvivors()).toEqual(1);
        expect(attendeeList.getNumLosers()).toEqual(4);
      });

      it("knows who won by name", function () {
        attendeeList.loses("Chopin");
        attendeeList.loses("Mozart");
        attendeeList.loses("Beethoven");
        attendeeList.loses("Liszt");

        expect(attendeeList.isWinner("Bach")).toEqual(true);
        expect(attendeeList.isWinner("Chopin")).toEqual(false);
      });
    });

    describe("reset", function () {
      it("can reset attendees", function () {
        attendeeList.loses("Chopin");
        attendeeList.loses("Mozart");
        attendeeList.loses("Beethoven");

        attendeeList.reset();

        expect(attendeeList.getNumLosers()).toEqual(0);
        expect(attendeeList.getNumSurvivors()).toEqual(5);
      });
    });
  });
});
