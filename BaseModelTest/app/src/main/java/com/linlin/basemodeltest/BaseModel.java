package com.linlin.basemodeltest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by linlin on 9/27/16.
 * 利用java的反射机制写的model基类,使用注意:
 * 1.子类的一般类型(int,double,boolean,)的属性名需要跟Json字符串中的key一样,而且必须实现setter(可以自动生成,不能改名字).
 * 2.特殊类型的属性Map,属性名依然保持跟key一致,实现setter,属性类型是以key首字母大写的字符串为类名所创建数据模型(继承BaseModel).
 * 3.特殊类型的属性Array,属性名依然保持跟key一致,实现setter,属性类型是以key首字母大写的字符串为类名所创建数据模型(继承BaseModel)为type的ArrayList.
 * 4.子类必须重写带参数的构造方法,其他定义的属性需要特殊处理.
 * 5.所有子类必须跟BaseModel放在同一个package下
 */
public class BaseModel {

    public BaseModel() {
    }

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
            } else if (obj instanceof Boolean) {
                setValue(key, jsonObject.optBoolean(key), boolean.class);
            } else if (obj instanceof Integer) {
                setValue(key, jsonObject.optInt(key), int.class);
            } else if (obj instanceof Double) {
                setValue(key, jsonObject.optDouble(key), double.class);
            } else if (obj instanceof JSONObject) {
                String entityClassName = getClassNameFromKey(key);
                try {
                    setValue(key, getJSONObject(jsonObject.optJSONObject(key), entityClassName), Class.forName(entityClassName));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (obj instanceof JSONArray) {
                String entityClassName = getClassNameFromKey(key);
                setValue(key, getJSONArray(jsonObject.optJSONArray(key), entityClassName), ArrayList.class);
            }
        }

    }

    private String getClassNameFromKey(String key) {
        if (key == null || key.length() == 0) return null;
        StringBuffer buffer = new StringBuffer();
        buffer.append(("" + key.charAt(0)).toUpperCase());
        buffer.append(key.substring(1, key.length()));

        return buffer.toString();
    }

    private void setValue(String key, Object obj, Class<?> valueType) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("set");
        buffer.append(("" + key.charAt(0)).toUpperCase());
        buffer.append(key.substring(1, key.length()));
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

    //JSONArray特殊处理
    private ArrayList<Object> getJSONArray(JSONArray jsonArray, String entityClassName) {
        if (jsonArray == null) return null;
        ArrayList<Object> entities = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            if (entities == null) entities = new ArrayList<>();
            Object entity = getJSONObject(jsonArray.optJSONObject(i), entityClassName);
            entities.add(entity);
        }

        return entities;
    }

    //JSONObject特殊处理
    private Object getJSONObject(JSONObject jsonObject, String entityClassName) {
        Object entity = null;
        try {
            String packageName = this.getClass().getPackage().getName();
            Class<?> entityClass = Class.forName(packageName+'.'+entityClassName);//ClassLoader.getSystemClassLoader().loadClass(packageName+'.'+entityClassName);//
            Constructor<?> constructor = entityClass.getConstructor(JSONObject.class);
            entity = constructor.newInstance(jsonObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            return entity;
        }
    }

}
