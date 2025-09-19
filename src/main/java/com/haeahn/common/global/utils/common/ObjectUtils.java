package com.haeahn.common.global.utils.common;

public class ObjectUtils {
    private ObjectUtils() {
        throw new UnsupportedOperationException("ObjectUtils class");
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isAllNull(Object... objs) {
        for (Object obj : objs) {
            if (obj != null) return false;
        }
        return true;
    }

    public static boolean isAnyNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) return true;
        }
        return false;
    }
}
