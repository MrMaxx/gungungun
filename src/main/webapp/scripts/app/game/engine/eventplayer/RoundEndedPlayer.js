var RoundEndedPlayer = {

    play: function(event, stage){

        var gameBoard = stage.findOne('#GameBoard');
        gameBoard.setRound(event.round);
        setTimeout(EventDispatcher.playNext, 500);

    }
    
}