function GameEngine(events, arenaName, stage) {
    var self = this;
    this.ready = false;
    this.initialized = false;
    this.browser = $(document);
    this.arenaName = arenaName;
    this.stage = stage;

    this.events = events;


    function init() {
        self.ready = false;

        var backgroundLayer = new Konva.Layer({id: 'backgrounds'});
        var objectLayer = new Konva.Layer({id: 'objects'});

        var background = new Konva.Image({
            image: Konva.Assets[self.arenaName],
            width: 1000,
            height: 680
        });
        backgroundLayer.add(background);
        stage.add(backgroundLayer);
        stage.add(objectLayer);

        EventDispatcher.reset();
        EventDispatcher.stage = self.stage;
        EventDispatcher.events = self.events;

        self.ready = true;
        self.initialized = true;
    }

    this.run = function () {
        if (!this.initialized) {
            init();
        }

        var fightText = new Konva.Text({
            x: stage.getWidth() / 2,
            y: stage.getHeight() / 2,
            offset:{x:30,y:15},
            text: 'Fight',
            fontSize: 30,
            fontFamily: 'Calibri',
            fill: 'orange'
        });
        this.stage.findOne('#objects').add(fightText);

        fightText.setZIndex(10);

        var tween = new Konva.Tween({
            node: fightText,
            duration: 1,
            opacity: 0,
            easing: Konva.Easings.StrongEaseIn,
            scaleX: 30,
            scaleY: 30,
            onFinish: function(){
                EventDispatcher.playNext();
            }
        });
        tween.play();
    }
}