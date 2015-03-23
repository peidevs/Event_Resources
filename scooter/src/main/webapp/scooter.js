dojo.provide("scooter.src");

dojo.require("dojox.css3.fx");

// inspired by https://github.com/dojo/demos/tree/master/css3
// and uses Dojo - http://dojotoolkit.org/

dojo.declare("Scooter", null, {
    menuNode: null,
    constructor: function(){
        // ------------------- data
        var attendeeList = new AttendeeList(ATTENDEE_LIST);

        var numNames = attendeeList.getNumNames();
        
        // ------------------- Reset button
        var resetButton = dojo.create("button",{ innerHTML: "Reset",id: "resetButton" }, dojo.body());
        
        dojo.connect(resetButton, "onclick", function(){
            dojo.query(".box").forEach(function(node){
                dojo.style(node, {transform: "scale(1)", opacity: "1"});
                attendeeList.reset();
            });
        });
        // ------------------- Go button
        var goButton = dojo.create("button",{ innerHTML: "Go!",id: "goButton" }, dojo.body());
        
        dojo.connect(goButton, "onclick", function(){
            var loserAnimations = ["puff","shrink"];
            
            // Each person has a 1-in-N chance of losing this round.
            // Note it is possible for no one to lose in a given round.
            
            dojo.query(".box").forEach(function(node){
                var name = node.id;
                var isLoser = attendeeList.isLoserThisRound(name);
                
                if (isLoser) {
                    animation = new Utils().pickOne(loserAnimations); 
                    dojox.css3.fx[animation]({node: node}).play();                    
                }
            });
            
            if (attendeeList.doesWinnerExist()) {
                dojo.query(".box").forEach(function(node){
                    var name = node.id;
                    var isWinner = attendeeList.isWinner(name);

                    if (isWinner) {
                        dojox.css3.fx["rotate"]({node: node}).play();                                        
                    }
                });                
            }
        });
        
        // ------------------- boxes
        
        this.menuNode = dojo.create("div", {className: "menu"}, dojo.body());
        
        var numBoxesPerRow = 6;
        
        for(var i = 0; i < numNames; i++) {
            var name = attendeeList.getName(i);
            var box = dojo.create("div", {
                innerHTML: "<span id='boxText'>" + name + "</span>",
                className: "box",
                id: name,
                style: {
                    left: (i % numBoxesPerRow) * 200 + "px",
                    top: Math.floor(i / numBoxesPerRow)*175 + "px"
                }
            }, this.menuNode);
        }
    }
});

dojo.ready( function() { new Scooter(); } );
