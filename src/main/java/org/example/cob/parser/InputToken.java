package org.example.cob.parser;

public class InputToken {

    String type;
    String name;
    String label;
    int max;
    int min;

    InputToken(
            String type,
            String name,
            String label,
            int min,
            int max
    ){
        this.type = type;
        this.name = name;
        this.label = label;
        this.min = min;
        this.max = max;
    }

    String getName(){
        return name;
    }

    String getType(){
        return type;
    }

    String getLabel(){return label;}

    int getMin(){return min;}

    int getMax(){return max;}
}
