package expense;

import java.sql.Date;
public class Expense {
    private int num;
    private Date paydate;
    private int receiver;
    private double value;

    public Expense(int num, Date paydate, int receiver, double value) {
        this.num = num;
        this.paydate = paydate;
        this.receiver = receiver;
        this.value = value;
    }

    public int getNum() {
        return num;
    }

    public Date getPaydate() {
        return paydate;
    }

    public int getReceiver() {
        return receiver;
    }

    public double getValue() {
        return value;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public void setValue(double value) {
        this.value = value;
    }
}