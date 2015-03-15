var Direction = {

    NORTH: "NORTH",
    WEST: "WEST",
    SOUTH: "SOUTH",
    EAST: "EAST",

    getDirection: function(directionString){
        switch(directionString){
            case "NORTH": return Direction.NORTH;
            case "WEST": return Direction.WEST;
            case "SOUTH": return Direction.SOUTH;
            case "EAST": return Direction.EAST;
        }
        return Direction.NORTH;
    },
    /**
     * returns 1 for turning left and -1 for turning right
     */
    getKonvaRotation: function(fromDirection, toDirection){

        switch(fromDirection){
            case Direction.NORTH:
                if(toDirection == Direction.WEST){return -180;}
                if(toDirection == Direction.EAST){return 0;}
                if(toDirection == Direction.SOUTH){return 90;}
                break;
            case Direction.WEST:
                if(toDirection == Direction.SOUTH){return -270;}
                if(toDirection == Direction.NORTH){return 270;}
                if(toDirection == Direction.EAST){return 0;}
                break;
            case Direction.SOUTH:
                if(toDirection == Direction.EAST){return -360;}
                if(toDirection == Direction.WEST){return 180;}
                if(toDirection == Direction.NORTH){return 270;}
                break;
            case Direction.EAST:
                if(toDirection == Direction.NORTH){return -90;}
                if(toDirection == Direction.SOUTH){return 90;}
                if(toDirection == Direction.WEST){return 180;}
                break;
        }

        return 1;

    }


}