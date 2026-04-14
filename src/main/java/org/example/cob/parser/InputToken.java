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

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getLabel(){return label;}

    public int getMin(){return min;}

    public int getMax(){return max;}
}
