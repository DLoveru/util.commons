package pers.hai.util.commons.containers;

import com.google.common.collect.Lists;
import pers.hai.util.commons.EmptyUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 集合工具类
 */
public final class CollectionUtil {

    /**
     * 创建一个空ArrayList
     * 
     * @return
     */
    public static <E> List<E> newArrayList() {
        return Lists.newArrayList();
    }

    /**
     * 将元素转换为ArrayList, 相对于{@link Arrays#asList(Object...)} 来说<br>
     * 该方法不存在集合的由于类型不是ArrayList而导致的类型错误, 且与ArrayList一致可以继续添加元素
     * 
     * @param es 元素
     * @return
     */
    public static <E> List<E> newArrayList(E... es) {
        return Lists.newArrayList(es);
    }

    /**
     * 将元素中非NULL部分转换为ArrayList
     * 
     * @param es 元素
     * @return
     */
    public static <E> List<E> newArrayListNotNull(E... es) {
        List<E> list = new ArrayList<>();
        addNotNull(list, es);
        return list;
    }

    /**
     * 将元素中的非NULL部分转换为HashSet
     * 
     * @param es 元素
     * @return
     */
    public static <E> Set<E> newHashSetNotNull(E... es) {
        Set<E> set = new HashSet<>();
        addNotNull(set, es);
        return set;
    }

    /**
     * 将元素中的非NULL部分添加到集合中
     * 
     * @param collection 集合
     * @param es 元素
     */
    public static <E> void addNotNull(Collection<E> collection, E... es) {
        for (E e : es) {
            if (e != null) {
                collection.add(e);
            }
        }
    }

    /**
     * 获取集合的大小, 包含null判断（null 等于 0）
     * 
     * @param collection
     * @return
     */
    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * 获取List集合中的第一个元素，若集合为空，返回NULL
     * 
     * @param list 集合
     * @return E
     */
    public static <E> E getFirstElementOrNull(List<E> list) {
        return EmptyUtil.isEmpty(list) ? null : list.get(0);
    }


    /**
     * JAVA8-groupBy分组 简化
     * 
     * @param list 集合
     * @param classifier 分组方法
     * @return
     */
    public static <K, E> Map<K, List<E>> groupBy(List<E> list, Function<? super E, ? extends K> classifier) {
        return list.stream().collect(StreamUtil.groupingBy(classifier));
    }

    /**
     * JAVA8-toMap分组 简化
     * 
     * @param list 集合
     * @param keyMapper 分组方法
     * @return
     */
    public static <E, K> Map<K, E> toMap(List<E> list, Function<? super E, ? extends K> keyMapper) {
        Map<K, E> map = new HashMap<>();
        if (EmptyUtil.isEmpty(list)) {
            return map;
        }
        for (E e : list) {
            map.put(keyMapper.apply(e), e);
        }
        return map;
    }

    /**
     * JAVA8-filter过滤简化，过滤并返回List
     * 
     * @param list 集合
     * @param predicate 过滤方法
     * @return
     */
    public static <E> List<E> filterToList(List<E> list, Predicate<? super E> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * JAVA8-map优化，map并返回List
     * 
     * @param list 集合
     * @param mapper map方法
     * @return
     */
    public static <E, R> List<R> mapToList(List<E> list, Function<? super E, ? extends R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * [J8代码简化] 循环
     * 
     * @param list 集合
     * @param consumer (元素) => {}
     */
    public static <E> void forEach(List<E> list, Consumer<E> consumer) {
        for (E e : list) {
            consumer.accept(e);
        }
    }

    /**
     * JAVA8-forEach优化，带下标的forEach
     * 
     * @param list 集合
     * @param biConsumer 循环方法
     */
    public static <E> void forEach(List<E> list, BiConsumer<E, Integer> biConsumer) {
        for (int i = 0; i < list.size(); i++) {
            biConsumer.accept(list.get(i), i);
        }
    }

    /**
     * 取集合中最佳的一条，可以是最大值最小值或者其他，方法内部取第一条作为初始条件，遍历集合所有元素进行比较<br>
     * 一般{@link Collections#max},或者{@link Collections#min} 等若需要传入比较器的非简单数值比较时，可以使用该方法代替实现会更清晰
     * 
     * @param collection 集合
     * @param compare 比较器，返回两条中的一条，该条会作为下次比较的第一参数
     * @return 若集合为空返回null，否则根据比较器返回最佳的元素
     */
    public static <E> E best(Collection<? extends E> collection, BinaryOperator<E> compare) {
        if (EmptyUtil.isEmpty(collection)) {
            return null;
        }
        Iterator<? extends E> i = collection.iterator();
        E candidate = i.next();
        while (i.hasNext()) {
            candidate = compare.apply(candidate, i.next());
        }
        return candidate;
    }

    /**
     * 使用条件将集合分成两个集合
     * 
     * @param list 集合
     * @param predicate 条件，符合条件为第一个集合，否则为第二个集合
     * @return
     */
    public static <E> Collect<E> collect(List<E> list, Predicate<? super E> predicate) {
        List<E> first = new ArrayList<>();
        List<E> second = new ArrayList<>();
        for (E e : list) {
            if (predicate.test(e)) {
                first.add(e);
            } else {
                second.add(e);
            }
        }
        return new Collect<>(first, second);
    }

    /**
     * 使用条件将集合分成两个集合
     * 
     * @param list 集合
     * @param firstPredicate 集合A
     * @param secondPredicate 集合B
     * @return
     */
    public static <E> Collect<E> collect(List<E> list, Predicate<? super E> firstPredicate, Predicate<? super E> secondPredicate) {
        List<E> first = new ArrayList<>();
        List<E> second = new ArrayList<>();
        for (E e : list) {
            if (firstPredicate.test(e)) {
                first.add(e);
            }
            if (secondPredicate.test(e)) {
                second.add(e);
            }
        }
        return new Collect<>(first, second);
    }

    /**
     * 用于返回两个集合
     * @param <E>
     */
    public static class Collect<E> {
        private List<E> first;
        private List<E> second;

        public Collect(List<E> first, List<E> second) {
            this.first = first;
            this.second = second;
        }

        public List<E> getFirst() {
            return first;
        }

        public List<E> getSecond() {
            return second;
        }
    }

    private CollectionUtil() {}
}
