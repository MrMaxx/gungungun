var EventDispatcher = {
    events: new Array(),
    stage:null,
    finished: false,
    index: 0,

    reset:function(){
        EventDispatcher.events = new Array();
        EventDispatcher.index = 0;
        EventDispatcher.stage = null;
        EventDispatcher.finished = false;
    },
    playNext: function() {
        if( EventDispatcher.index >= EventDispatcher.events.length ){
            return;
        }

        var nextEvent = EventDispatcher.events[EventDispatcher.index];

        switch (nextEvent.type)
        {
            case GameEventType.TOKEN_SPAWNED:
                TokenSpawnedEventPlayer.play(nextEvent, EventDispatcher.stage);
                break;
            case GameEventType.MOVE_TO:
                MoveToEventPlayer.play(nextEvent, EventDispatcher.stage);
                break;
            case GameEventType.TURN_TO:
                TurnToEventPlayer.play(nextEvent, EventDispatcher.stage);
                break;
            case GameEventType.SHOOT:
                ShootAtEventPlayer.play(nextEvent, EventDispatcher.stage);
                break;
            case GameEventType.TOKEN_DIED:
                TokenDiedEventPlayer.play(nextEvent, EventDispatcher.stage);
                break;
            default:
                Logger.error("No EventPlayer has been found for Event of type = "+nextEvent.type+" skipping this event.");
                EventDispatcher.index = EventDispatcher.index + 1;
                EventDispatcher.playNext();
                return;
                break;
        }
        EventDispatcher.index = EventDispatcher.index + 1;

    }

}