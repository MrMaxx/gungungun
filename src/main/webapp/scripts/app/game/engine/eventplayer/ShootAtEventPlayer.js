var ShootAtEventPlayer = {

    event: null,
    stage: null,

    play: function(event, stage){

        ShootAtEventPlayer.event = event;
        ShootAtEventPlayer.stage = stage;

        var heroToken = stage.findOne('#'+event.elementId);
        var targetToken = stage.findOne('#'+event.targetId);

        var fieldsToTravel = Math.max(
            Math.abs(heroToken.getCoordinate().x-targetToken.getCoordinate().x),
            Math.abs(heroToken.getCoordinate().y-targetToken.getCoordinate().y));

        heroToken.shootAt(
            targetToken.getCoordinate().x,
            targetToken.getCoordinate().y,
            fieldsToTravel*0.05,
            ShootAtEventPlayer.inflictDamage
        );
    },
    inflictDamage: function(){
        var target = ShootAtEventPlayer.stage.findOne('#'+ShootAtEventPlayer.event.targetId);

        target.receiveDamage(ShootAtEventPlayer.event.damage ,EventDispatcher.playNext);
    }
    
}