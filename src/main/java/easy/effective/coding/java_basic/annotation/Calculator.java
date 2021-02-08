package easy.effective.coding.java_basic.annotation;

public class Calculator {

    @Check
    public void add() {
        String str = null;
        str.toString();
        System.out.println("1+0=" + (1 + 0));
    }

    @Check
    public void div() {
        System.out.println("1/0=" + (1 / 0));
    }

    @Check
    public void show(){
        System.out.println("test bug");
    }
}


