/**
 * 数组
 *
 * @param <E>
 */
public class Array<E> {

    private E[] data;

    private int size;

    Array() throws Exception {
        this(10);
    }

    Array(int capacity) throws Exception {
        if (capacity <= 0) {
            throw new Exception("参数不合法");
        }
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("参数不合法");
        }

        if (size == data.length) {
            //扩容
            resize(2 * data.length);
        }

        if (size - index >= 0) System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = e;
        size++;
    }

    public void addFirst(E e) throws Exception {
        add(0, e);
    }

    public void addLast(E e) throws Exception {
        add(size, e);
    }

    public E get(int index) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("参数不合法");
        }
        return data[index];
    }

    public void set(int index, E e) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("参数不合法");
        }
        data[index] = e;
    }

    public E getFirst() throws Exception {
        return get(0);
    }

    public E getLast() throws Exception {
        return get(size - 1);
    }

    public boolean contains(E e) {
        for (int i = 1; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public E remove(int index) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("参数不合法");
        }
        E e = data[index];
        if (size - index + 1 >= 0) System.arraycopy(data, index + 1, data, index + 1 - 1, size - index + 1);
        size--;
        data[size] = null;
        //缩容
        if (size == (data.length / 4) && (data.length / 2) != 0) {
            resize(data.length / 2);
        }
        return e;
    }

    public E removeFirst() throws Exception {
        return remove(0);
    }

    public E removeLast() throws Exception {
        return remove(size - 1);
    }

    public void removeElement(E e) throws Exception {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    /**
     * 更新容量
     */
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        if (size >= 0) System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    @Override
    public String toString() {
        if (data == null)
            return "null";

        int iMax = data.length - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(data[i]));
            if (i == iMax) {
                b.append(",size=").append(size);
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }
}
