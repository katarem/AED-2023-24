package UD1.ej1_3;

import java.util.ArrayList;
import java.util.List;

class ImplementaMinimoMaximo<T extends Comparable<T>> implements MinimoMaximo<T> {

    ArrayList<T> list;

    public ImplementaMinimoMaximo(ArrayList<T> list){
        this.list = list;
    }

    public void setList(ArrayList<T> list){
        this.list = list;
    }


    @Override
    public T minimo() {
        T min = list.get(0);
        for (T item: list) {
            if(item.compareTo(min)<0) min = item;
        }
        return min;
    }

    @Override
    public T maximo() {
        T max = list.get(0);
        for (T item: list) {
            if(item.compareTo(max)>0) max = item;
        }
        return max;
    }
}
