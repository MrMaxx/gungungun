Grunt = function(config){
    this._____init(config);
};
Grunt.prototype = {
    _____init:function(config){
        var baseConfig = {
            x: (config.x*40)+20,
            y: (config.y*40)+20,
            coordinate:{x:config.x,y:config.y},
            id: config.id,
            name:'object',
            image:Konva.Assets.GRUNT,
            width: 40,
            height: 40,
            offset:{x:20,y:20}
        };
        Token.call(this, baseConfig);
        this.className = 'Grunt';
        this.speed = 30;
    }
};

Konva.Util.extend(Grunt, Token);