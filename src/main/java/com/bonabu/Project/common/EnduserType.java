package com.bonabu.Project.common;

public  class EnduserType {

    public static String TypeToSring(int x)
    {
        if (x==1)
            return "دانشجو";
        else if (x==2)
            return "کارمند";
        else if (x==3)
            return "هیت علمی";
        return "";
    }
}
