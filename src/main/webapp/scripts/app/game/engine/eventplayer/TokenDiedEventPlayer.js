var TokenDiedEventPlayer = {

    play: function(event, stage){

        var heroToken = stage.findOne('#'+event.elementId);
        heroToken.destroyToken(EventDispatcher.playNext);
    }

}