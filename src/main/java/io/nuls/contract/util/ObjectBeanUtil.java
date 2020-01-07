package io.nuls.contract.util;

import io.nuls.contract.sdk.Address;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectBeanUtil {
    public static final  List<String> WRAPPER_CLASS=new ArrayList<String>();
    public static final Map<String,Class> BASIC_CLASS=new HashMap<String,Class>();
    static {
        WRAPPER_CLASS.add("ava.util.ArrayList");
        WRAPPER_CLASS.add("java.util.List");
        WRAPPER_CLASS.add("java.util.concurrent.CopyOnWriteArrayList");
        WRAPPER_CLASS.add("java.util.LinkedList");
        WRAPPER_CLASS.add("java.util.Stack");
        WRAPPER_CLASS.add("java.util.Vector");
        WRAPPER_CLASS.add("java.util.Map");
        WRAPPER_CLASS.add("java.util.HashMap");
        WRAPPER_CLASS.add("java.util.Hashtable");
        WRAPPER_CLASS.add("java.util.SortedMap");

        BASIC_CLASS.put("java.lang.Byte",byte.class);
        BASIC_CLASS.put("java.lang.Short",short.class);
        BASIC_CLASS.put("java.lang.Integer",int.class);
        BASIC_CLASS.put("java.lang.Long",long.class);
        BASIC_CLASS.put("java.lang.Float",float.class);
        BASIC_CLASS.put("java.lang.Double",double.class);
        BASIC_CLASS.put("java.lang.Character",char.class);
        BASIC_CLASS.put("java.lang.Boolean",boolean.class);
    }
}
