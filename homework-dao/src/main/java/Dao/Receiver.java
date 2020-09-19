package Dao;

import java.io.Serializable;

public class Receiver implements Serializable {

    private int num;
    private String name;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dao.Receiver{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}
