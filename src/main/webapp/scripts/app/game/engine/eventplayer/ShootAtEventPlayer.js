var ShootAtEventPlayer = {

    play: function(event, stage){


        var heroToken = stage.findOne('#'+event.elementId);
        var targetToken = stage.findOne('#'+event.targetId);

        var fieldsToTravel = Math.max(
            Math.abs(heroToken.getCoordinate().x-targetToken.getCoordinate().x),
            Math.abs(heroToken.getCoordinate().y-targetToken.getCoordinate().y));

        heroToken.shootAt(
            targetToken.getCoordinate().x,
            targetToken.getCoordinate().y,
            fieldsToTravel*0.05,
            EventDispatcher.playNext
        );
    }
    
}