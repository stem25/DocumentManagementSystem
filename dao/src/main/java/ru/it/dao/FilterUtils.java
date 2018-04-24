package ru.it.dao;

import java.util.Map;

public class FilterUtils {

    public static void initFilter(Map<String, String> filter, String prefix, StringBuilder sql){
        if(filter == null || filter.isEmpty()) return;
        sql.append("\n WHERE 1=1 AND\n");
        for (String str: filter.keySet()){
            sql
                    .append(prefix)
                    .append(".")
                    .append(str)
                    .append(" = ")
                    .append(filter.get(str));
        }
    }
}
