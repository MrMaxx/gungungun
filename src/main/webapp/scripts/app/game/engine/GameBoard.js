GameBoard = function(config){
    this.____init(config);
};

GameBoard.prototype = {

    ____init:function(){


        Konva.Group.call(this, {
            x: 420,
            y:10,
            id: "GameBoard"
        });

        var backgroundBox = new Konva.Rect({
            x: 0,
            y: 0,
            width: 160,
            height: 40,
            fill: 'white',
            stroke: 'black',
            strokeWidth: 2
        });

        this.roundText = new Konva.Text({
            x: 20,
            y: 8,
            text: 'Round:  0',
            fontSize: 30,
            fontFamily: 'Calibri',
            fill: 'black'
        });

        this.add(backgroundBox);
        this.add(this.roundText);

    },
    setRound: function(round){
        this.roundText.destroy();
        this.roundText = new Konva.Text({
            x: 20,
            y: 8,
            text: 'Round: '+round,
            fontSize: 30,
            fontFamily: 'Calibri',
            fill: 'black'
        });
        this.add(this.roundText);
    }
};

Konva.Util.extend(GameBoard, Konva.Group);