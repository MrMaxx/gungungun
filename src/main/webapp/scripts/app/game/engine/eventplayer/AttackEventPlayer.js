var AttackEventPlayer = {

    event: null,
    stage: null,

    play: function(event, stage){

        AttackEventPlayer.event = event;
        AttackEventPlayer.stage = stage;

        var heroToken = stage.findOne('#'+event.elementId);
        var targetToken = stage.findOne('#'+event.targetId);


        heroToken.attack(
            targetToken.getCoordinate().x,
            targetToken.getCoordinate().y,
            0.6,
            AttackEventPlayer.inflictDamage
        );
    },
    inflictDamage: function(){
        var target = AttackEventPlayer.stage.findOne('#'+AttackEventPlayer.event.targetId);
        if(target){
            target.receiveDamage(AttackEventPlayer.event.damage ,EventDispatcher.playNext);
        }else{
            EventDispatcher.playNext();
        }

    }
    
}