/**
 * inspired by https://github.com/dojo/demos/tree/master/css3
 * and uses Dojo - http://dojotoolkit.org/
 */
define(['dojo/_base/declare',
    'dojo/dom-construct',
    'dojo/dom-style',
    'dijit/_WidgetBase',
    'dijit/_TemplatedMixin',
    'dijit/_WidgetsInTemplateMixin',
    'scooter/AttendeeList',
    'scooter/utils',
    'dojox/css3/fx',
    'dojo/text!scooter/template/scooter.html'], function (declare, domConstruct, domStyle, _WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin, AttendeeList, utils, cssFX, template) {
    return declare("Scooter", [_WidgetBase, _TemplatedMixin, _WidgetsInTemplateMixin], {
        //These elements will be populated by the template
        _menuNode: null,

        templateString: template,

        //private variables used in the app
        _boxes: [],
        _attendeeList: AttendeeList([]),

        _setAttendeeListAttr: function (attendees) {
            this._attendeeList.init(attendees);
        },

        postCreate: function () {

            var numNames = this._attendeeList.getNumNames();

            var numBoxesPerRow = 6;

            for (var i = 0; i < numNames; i++) {
                var name = this._attendeeList.getName(i);
                var box = domConstruct.create("div", {
                    innerHTML: "<span id='boxText'>" + name + "</span>",
                    className: "box",
                    id: name,
                    style: {
                        left: (i % numBoxesPerRow) * 200 + "px",
                        top: Math.floor(i / numBoxesPerRow) * 175 + "px"
                    }
                }, this._menuNode);

                this._boxes.push(box);
            }
        },

        _resetClickEvent: function () {
            this._boxes.forEach(function (node) {
                domStyle.set(node, {
                    "transform": "scale(1)",
                    "opacity": "1"
                });

                this._attendeeList.reset();
            }, this);
        },

        _goClickEvent: function () {
            var loserAnimations = ["puff", "shrink"];

            // Each person has a 1-in-N chance of losing this round.
            // Note it is possible for no one to lose in a given round.

            this._boxes.forEach(function (node) {
                var name = node.id;
                var isLoser = this._attendeeList.isLoserThisRound(name);

                if (isLoser) {
                    animation = utils.pickOne(loserAnimations);
                    cssFX[animation]({node: node}).play();
                }
            }, this);

            if (this._attendeeList.doesWinnerExist()) {
                this._boxes.forEach(function (node) {
                    var name = node.id;
                    var isWinner = this._attendeeList.isWinner(name);

                    if (isWinner) {
                        cssFX["rotate"]({node: node}).play();
                    }
                }, this);
            }
        }
    });
});
