package oobbs.presentation.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import oobbs.Constants;

/**
 * Both NavigationNode and NavigationPathBuilder are presentation layer artifacts!
 * No services dependent them!
 *
 * In a way, NavigationNode is a type of DTO!
 */
public class NavigationNode implements Serializable{

    private static final long serialVersionUID = 6579747691750913282L;

    private String name;

    //Flyweight? action is statble, name and params are unstable!
    private String action;

    private Map<String, Object> paramMap = new HashMap<String, Object>();

    /*---------------------------------    Main Logic Methods    ---------------------------------*/

    public void addParam(String name,Object value){
        paramMap.put(name, value);
    }

    public String getRequestPath(){
        StringBuilder path = new StringBuilder("/oobbs/site/");
        path.append(action).append(".action?");
        Set<Map.Entry<String, Object>> params = paramMap.entrySet();
        for (Map.Entry<String, Object> param : params) {
            path.append(param.getKey()).append("=").append(param.getValue()).append("&");
        }
        return path.toString();
    }
    /*----------------------------------    Accessor Methods    ----------------------------------*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
