package ite2017;

import java.util.ArrayList;
import java.util.List;

public class SequenceShip {
    List<Ship> array = new ArrayList<Ship>();
    int size;

    public SequenceShip() {
        size = 0;
    }
    public  SequenceShip(List<Ship> list){
        array=list;
        size=list.size();
    }
    Iteratorinterface getIterator(){
        return new MyIterator();
    }
    class MyIterator implements Iteratorinterface {
        int index=0;
        @Override
        public boolean hasnext() {
            return index<array.size();
        }

        @Override
        public Ship getnext() {
            return array.get(index++);
        }
    }
}
