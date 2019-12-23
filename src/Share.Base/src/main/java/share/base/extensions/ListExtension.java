package share.base.extensions;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class ListExtension {

    public static <T> List<T> asList(Iterable<T> iter) {
        List<T> copy = new ArrayList<>();
        Iterator<T> iterator = iter.iterator();
        while (iterator.hasNext()) {
            copy.add(iterator.next());
        }
        return copy;
    }

    public static <T> List<T> asList(Enumeration<T> iter) {
        List<T> copy = new ArrayList<>();
        while (iter.hasMoreElements()) {
            copy.add(iter.nextElement());
        }
        return copy;
    }

    public static <T> List<List<T>> asSplitList(List<T> list, int len) {
        if (list == null || list.size() == 0 || len < 1) {
            return null;
        }
        List<List<T>> result = new ArrayList<>();

        int size = list.size();
        int count = (size + len - 1) / len;

        for (int i = 0; i < count; i++) {
            List<T> subList = list.subList(i * len, ((i + 1) * len > size ? size : len * (i + 1)));
            result.add(subList);
        }
        return result;
    }

    public static boolean isNullOrEmpty(List ls) {
        return ls == null || ls.isEmpty();
    }
}
