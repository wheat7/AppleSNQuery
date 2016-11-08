package com.wheat7.applesnquery;

/**
 * Created by Wheat7 on 08/11/2016.
 */

public class Result {

    private String item;

    private String info;

    public Result(String item,String info){
        this.item=item;
        this.info=info;
    }

    public String getItem(){
        return item;
    }

    public String getInfo(){
        return info;
    }


}
