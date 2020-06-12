package pers.hai.util.commons;


import java.util.Collection;
import java.util.Map;

/**
 * 判空工具类
 *
 */
public class EmptyUtil {
    private EmptyUtil() {}

    /**
     * 字符串不为空，包含空格的字符串为true
     *
     * @param value
     * @return
     */
    public static boolean isNotEmpty(String value) {
        return !isEmpty(value);
    }

    /**
     * 空或者空字符串为true
     *
     * @param value
     * @return
     */
    public static boolean isEmpty(String value) {
        return value == null || value.length() < 1;
    }

    public static boolean isAllEmpty(String[] stringArray) {
        if (stringArray == null)
            return true;
        for (int i = 0; i < stringArray.length; i++) {
            if (!isEmpty(stringArray[i]))
                return false;
        }
        return true;
    }

    public static boolean hasOneEmpty(String[] stringArray) {
        if (stringArray == null)
            return false;
        for (int i = 0; i < stringArray.length; i++) {
            if (isEmpty(stringArray[i]))
                return true;
        }
        return false;
    }

    /**
     * null、空字符串或者空格的字符串都为true isTrimEmpty(null) = true isTrimEmpty("") = true isTrimEmpty(" ") = true isTrimEmpty("bob") = false
     * isTrimEmpty(" bob ") = false
     *
     * @param value
     * @return
     */
    public static boolean isTrimEmpty(String value) {
        int strLen;
        if (value == null || (strLen = value.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <pre>
     * isNotBlank(null)      = false
     * isNotBlank("")        = false
     * isNotBlank(" ")       = false
     * isNotBlank("bob")     = true
     * isNotBlank("  bob  ") = true
     * </pre>
     *
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value) {
        return !isTrimEmpty(value);
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length < 1;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    @SuppressWarnings("rawtypes")
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    /**
     * 如果集合为空，则返回0；不为空，返回具体的个数
     * @param collection
     * @return
     */
    public static int size(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        return collection.size();
    }

    /**
     * 如果集合为空，则返回0；不为空，返回具体的个数
     * @param map
     * @return
     */
    public static int size(Map<?, ?> map) {
        if (map == null || map.isEmpty()) {
            return 0;
        }
        return map.size();
    }

    /**
     * 当字符串为空字符串时，给一个默认值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String defaultString(String value, String defaultValue) {
        return isEmpty(value) ? defaultValue : value;
    }

    /**
     * 当对象为null时，给一个默认值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static Object defaultValue(Object value, Object defaultValue) {
        return value == null ? defaultValue : value;
    }

    /**
     * 当对象为null时，给一个默认值String值
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String defaultStringValue(Object value, String defaultValue) {
        return value == null ? defaultValue : value.toString();
    }
}
