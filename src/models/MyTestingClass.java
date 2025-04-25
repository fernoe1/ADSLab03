package models;

public class MyTestingClass {
    private int something;

    public MyTestingClass(int something) {
        this.something = something;
    }

    @Override
    public int hashCode() {
        return something;
    }
}
