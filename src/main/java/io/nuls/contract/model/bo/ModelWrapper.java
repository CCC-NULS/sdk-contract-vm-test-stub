package io.nuls.contract.model.bo;

public class ModelWrapper<T> {
    private T t;

    public ModelWrapper() {
    }

    public ModelWrapper(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
