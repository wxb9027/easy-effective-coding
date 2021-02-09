package easy.effective.coding.java_basic.juc;

public class TestNativeSon extends TestNativeFather {

    @Override
    public void print(String str) {
        System.out.println("son extends father's method!");
    }

    @Override
    public void native1(int x) {
        super.native1(x);
    }

    @Override
    protected void native2(int[] arry) throws Exception {
        super.native2(arry);
    }

    @Override
    synchronized float native3(Object o) {
        return super.native3(o);
    }
}
