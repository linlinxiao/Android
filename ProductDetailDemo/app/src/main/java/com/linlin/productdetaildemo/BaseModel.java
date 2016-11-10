package com.linlin.productdetaildemo;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by linlin on 9/27/16.
 * 利用java的反射机制写的model基类,子类的属性名需要跟Json字符串中的key一样,而且必须实现setter(可以自动生成,不能改名字).特殊类型的属性,如Array,Map
 * 需要重写构造方法,特殊处理.
 */
public class BaseModel {

    public BaseModel(){}

    public BaseModel(JSONObject jsonObject) {
        setAttribute(jsonObject);
    }

    private void setAttribute(JSONObject jsonObject) {
        if (jsonObject == null) return;
        Iterator<String> it = jsonObject.keys();
        while (it.hasNext()) {
            String key = it.next();
            Object obj = jsonObject.opt(key);
            if (obj instanceof String) {
                setValue(key, jsonObject.optString(key), String.class);
            }else if (obj instanceof Boolean) {
                setValue(key, jsonObject.optBoolean(key), boolean.class);
            }else if (obj instanceof Integer) {
                setValue(key, jsonObject.optInt(key), int.class);
            }else if (obj instanceof Double) {
                setValue(key, jsonObject.optDouble(key), double.class);
            }
        }
    }

    private void setValue(String key, Object obj, Class<?> valueType){
        StringBuffer buffer = new StringBuffer();
        buffer.append("set");
        buffer.append(("" + key.charAt(0)).toUpperCase());
        buffer.append(key.substring(1,key.length()));
        try {
            Method method = this.getClass().getDeclaredMethod(buffer.toString(), valueType);
            try {
                method.invoke(this, obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
