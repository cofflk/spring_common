package com.haeahn.common.global.utils.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypeUtils {

    private TypeUtils() {
        throw new UnsupportedOperationException("TypeUtils class");
    }

    public String getType(Object obj) {
//        if (obj == null) return "null";
//        return obj.getClass().getName();
        return switch (obj) {
            case null -> "null";
            case String s -> "string";
            case Integer i -> "integer";
            case Long l -> "long";
            case Boolean b -> "boolean";
            case Double d -> "double";
            case Float f -> "float";
            case Map<?, ?> m -> "map";
            case List<?> l -> "list";
            case Set<?> s -> "set";
            case Object o -> "object";
        };
    }

//    type 별 처리 example1
//    public void handleValue(Object value1) {
//        switch (value1) {
//            case null -> System.out.println("Null value");
//            case String s -> System.out.println("문자열: " + s);
//            case Map<?, ?> m -> System.out.println("Map 크기: " + m.size());
//            case List<?> l -> System.out.println("List 길이: " + l.size());
//            default -> System.out.println("기타 타입: " + value1.getClass().getSimpleName());
//        }
//    }


//    type 별 처리 example2
//    public record ValueInfo(String type, Object value) {}
//    public ValueInfo getValueInfo(Object value1) {
//        return switch (value1) {
//            case null -> new ValueInfo("null", null);
//            case String s -> new ValueInfo("string", s);
//            case Integer i -> new ValueInfo("integer", i);
//            case Long l -> new ValueInfo("long", l);
//            case Boolean b -> new ValueInfo("boolean", b);
//            case Double d -> new ValueInfo("double", d);
//            case Float f -> new ValueInfo("float", f);
//            case Map<?, ?> m -> new ValueInfo("map", m);
//            case List<?> l -> new ValueInfo("list", l);
//            case Set<?> s -> new ValueInfo("set", s);
//            case Object o -> new ValueInfo("object", o);
//        };
//    }

    public static boolean isString(Object obj) {
        return obj instanceof String;
    }

    public static boolean isMap(Object obj) {
        return obj instanceof java.util.Map;
    }

    public static boolean isList(Object obj) {
        return obj instanceof java.util.List;
    }

    public static boolean isNumber(Object obj) {
        return obj instanceof Number;
    }

    public static boolean isBoolean(Object obj) {
        return obj instanceof Boolean;
    }

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }
}
