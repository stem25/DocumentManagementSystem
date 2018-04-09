package ru.it.utils;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {

    public static Map<String, String> getMap(UriInfo uriInfo){
        MultivaluedMap<String, String> filter = uriInfo.getQueryParameters();
        Map<String, String> result = new HashMap<>();
        if(filter.isEmpty()) return result;
        for(String str: filter.keySet()){
            result.put(str, filter.get(str).get(0));
        }
        return result;
    }
}
