package advent2015;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Array<T> {
    private final ArrayList<T> objects;

    public Array(T... args) {
        this(Arrays.asList(args));
    }

    public Array(Collection<T> args) {
        this.objects = new ArrayList<T>(args);
    }

    public Array(Stream<T> args) {
        this(args.collect(Collectors.toList()));
    }

    public Array(String s) {
        objects = new ArrayList<T>(s.length());
        for (Character c : s.toCharArray())
            objects.add((T)c);
    }


    public int size() {
        return objects.size();
    }

    public boolean isEmpty() {
        return objects.isEmpty();
    }

    public boolean contains(T o) {
        return objects.contains(o);
    }


    public T get(int index) {
        return objects.get(index);
    }

    public int indexOf(T o) {
        return objects.indexOf(o);
    }


    public int lastIndexOf(T o) {
        return objects.lastIndexOf(o);
    }


    public Array<T> subList(int fromIndex, int toIndex) {
        return new Array<T>(objects.subList(fromIndex, toIndex));
    }


    public boolean containsAll(Collection c) {
        return objects.containsAll(c);
    }


    public Array<T> filter(Predicate<? super T> predicate) {
        return new Array<T>(objects.stream().filter(predicate));
    }


    public <R> Array<R> map(Function<? super T, ? extends R> mapper) {
        return new Array<>(objects.stream().map(mapper));
    }


    public Array<T> distinct() {
        return new Array<T>(objects.stream().distinct());
    }


    public Array<T> sorted() {
        return new Array<T>(objects.stream().sorted());
    }


    public Array<T> sorted(Comparator<? super T> comparator) {
        return new Array<>(objects.stream().sorted(comparator));
    }


    public Array<T> limit(long maxSize) {
        return new Array<>(objects.stream().limit(maxSize));
    }


    public Array<T> skip(long n) {
        return new Array<>(objects.stream().skip(n));
    }


    public void forEach(Consumer<? super T> action) {
        objects.forEach(action);
    }


    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return objects.stream().reduce(identity, accumulator);
    }


    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return objects.stream().reduce(accumulator);
    }

    
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return objects.stream().reduce(identity, accumulator, combiner);
    }

    
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return objects.stream().collect(supplier, accumulator, combiner);
    }

    
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return objects.stream().collect(collector);
    }

    
    public Optional<T> min(Comparator<? super T> comparator) {
        return objects.stream().min(comparator);
    }

    
    public Optional<T> max(Comparator<? super T> comparator) {
        return objects.stream().max(comparator);
    }

    
    public long count() {
        return objects.size();
    }

    
    public boolean anyMatch(Predicate<? super T> predicate) {
        return objects.stream().anyMatch(predicate);
    }

    
    public boolean allMatch(Predicate<? super T> predicate) {
        return objects.stream().allMatch(predicate);
    }

    
    public boolean noneMatch(Predicate<? super T> predicate) {
        return objects.stream().noneMatch(predicate);
    }


    public Optional<T> findFirst() {
        return objects.stream().findFirst();
    }


    public Optional<T> findAny() {
        return objects.stream().findAny();
    }

    public void forEachIndexed(WithIndex<T, Void> func) {
        for (int i = 0; i < objects.size(); i++) {
            func.apply(i, objects.get(i));
        }
    }

    public <R> Array<R> mapIndexed(WithIndex<T, R> mapper) {
        ArrayList<R> temp = new ArrayList<R>(objects.size());
        for (int i = 0; i < objects.size(); i++) {
            temp.add(mapper.apply(i, objects.get(i)));
        }
        return new Array<R>(temp);
    }

    public Array<T> filterIndexed(WithIndex<T, Boolean> predicate) {
        ArrayList<T> temp = new ArrayList<>(objects.size());
        for (int i = 0; i < objects.size(); i++) {
            if (predicate.apply(i, get(i)))
                temp.add(get(i));
        }
        return new Array<>(temp);
    }

    public List<T> toList() {
        return objects;
    }


    public static class Pair<A, B> {
        public final A first;
        public final B second;

        public Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }
    }

}

interface WithIndex<T, U> {
    U apply(int index, T obj);
}
