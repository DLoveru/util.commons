package pers.hai.util.commons.containers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Java-8 流支持类
 *
 */
public class StreamUtil {

    private StreamUtil() {}

    /**
     * 根据Function分组<br>
     * 该方法为了兼容{@code Collectors.groupingBy} 不允许为空健的问题
     * 
     * @param classifier
     */
    public static <T, K> Collector<T, ?, Map<K, List<T>>> groupingBy(Function<? super T, ? extends K> classifier) {
        return Collectors.toMap(classifier, k -> {
            List<T> list = new ArrayList<>();
            list.add(k);
            return list;
        }, (List<T> oldList, List<T> newEl) -> {
            List<T> newList = new ArrayList<>(oldList.size() + 1);
            newList.addAll(oldList);
            newList.addAll(newEl);
            return newList;
        });
    }
}
