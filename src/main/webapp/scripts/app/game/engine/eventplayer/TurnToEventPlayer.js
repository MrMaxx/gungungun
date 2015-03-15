var TurnToEventPlayer = {

    play: function(event, stage){

        var heroToken = stage.findOne('#'+event.elementId);
        heroToken.turnTo(
            event.direction,
            0.5,
            EventDispatcher.playNext
        );
    }

}