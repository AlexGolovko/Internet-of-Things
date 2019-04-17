

public class Main {
    static {
        System.loadLibrary("java_c");
    }
    private native int fntest();
    public static void main(String[] args) {

        try{
            System.out.println("java_test");
            int junk=new Main().fntest();
            System.out.println("java test"+junk);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
