var MoveToEventPlayer = {

    play: function(event, stage){


        var heroToken = stage.findOne('#'+event.elementId);
        heroToken.moveTo(
            event.coordinate.x,
            event.coordinate.y,
            1,
            EventDispatcher.playNext
        );
    }
}