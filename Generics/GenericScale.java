package Generics;

public class GenericScale<T extends Comparable<T>> {
    private T left;
    private T right;

    public GenericScale(T left, T right) {
        this.left = left;
        this.right = right;
    }

    public T getLeft() {
        return left;
    }

    public void setLeft(T left) {
        this.left = left;
    }

    public T getRight() {
        return right;
    }

    public void setRight(T right) {
        this.right = right;
    }

    public T getHeavier() {
        if (this.left.compareTo(this.right) == 0) return null;
        if (this.left.compareTo(this.right) < 0) return right;
        return left;
    }
}


