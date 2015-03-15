dojo.provide("scooter.src");

dojo.require("dojox.css3.fx");

// inspired by https://github.com/dojo/demos/tree/master/css3
// and uses Dojo - http://dojotoolkit.org/

dojo.declare("Scooter", null, {
    menuNode: null,
    increment: 360,
    angle: 0,
    constructor: function(){
        // ------------------- data
        var attendeeList = new AttendeeList(ATTENDEE_LIST);

        var numNames = attendeeList.getNumNames();
        
        // ------------------- reset button
        var resetBtn = dojo.create("button", {
            innerHTML: "Reset",
            style: {
                position: "absolute",
                right: "10px",
                top: "30px",
                background: "#ccc",
                color: "#222",
                fontSize: "1em",
                lineHeight: "1.4em",
                width: "200px",
                textAlign: "center"
            }
        }, dojo.body());
        dojo.connect(resetBtn, "onclick", function(){
            dojo.query(".box").forEach(function(node){
                dojo.style(node, {transform: "scale(1)", opacity: "1"});
                attendeeList.reset();
            });
        });
        // ------------------- Draw button
        var drawBtn = dojo.create("button", {
            innerHTML: "Draw",
            style: {
                position: "absolute",
                right: "10px",
                top: "80px",
                background: "#ccc",
                color: "#222",
                fontSize: "1em",
                lineHeight: "1.4em",
                width: "200px",
                textAlign: "center"
            }
        }, dojo.body());
        dojo.connect(drawBtn, "onclick", function(){
            var loserAnimations = ["puff","shrink"];
            var count = 0;
            dojo.query(".box").forEach(function(node){
                var name = node.id;
                var isLoser = attendeeList.isLoserThisRound(name);
                
                if (isLoser) {
                    animation = loserAnimations[count % 2]; 
                    dojox.css3.fx[animation]({node: node}).play();                    
                }
                count++;
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
        
        this.increment = 360 / numNames;
        this.menuNode = dojo.create("div", {
            className: "menu"
        }, dojo.body());
        
        var numBoxesPerRow = 7;
        
        for(var i = 0; i < numNames; i++){
            var name = attendeeList.getName(i);
            var box = dojo.create("div", {
                innerHTML: "<span>" + name + "</span>",
                className: "box",
                id: name,
                style: {
                    left: (i % numBoxesPerRow) * 200 + "px",
                    top: Math.floor(i / numBoxesPerRow)*100 + "px"
                }
            }, this.menuNode);
        }
    }
});

dojo.ready( function() { new Scooter(); } );
