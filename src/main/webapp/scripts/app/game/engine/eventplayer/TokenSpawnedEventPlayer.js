var TokenSpawnedEventPlayer = {

    play: function(event, stage){

        var token;

        switch (event.tokenKey)
        {
            case TokenBlueprintKey.GRUNT:

                token = new Grunt({
                    health:80,
                    x: event.coordinate.x,
                    y: event.coordinate.y,
                    id: event.elementId
                });
                break;
            case TokenBlueprintKey.RUNNER:

                token = new Runner({
                    health:100,
                    x: event.coordinate.x,
                    y: event.coordinate.y,
                    id: event.elementId
                });
                break;
            default:
                Logger.error("No TokenBlueprintKey has been found for Event with tokenKey = "+event.tokenKey+" skipping this event.");
                setTimeout(function() {
                    EventDispatcher.playNext();
                }, 1000);
                return;
                break;
        }

        stage.findOne('#objects').add(token);

        token.spawnToken(function(){
            token.turnTo(
                event.direction,
                1,
                EventDispatcher.playNext
            );
        });
    }

}