package jie.flyer.portal;

/**
 * @author kain
 * @since 2020-03-30
 */
public class Ftest {

    public static final String a = "sadas";

    static {
        System.out.println("初始化了");
    }


    public static void main(String[] args) {
        int da = da();
        System.out.println(da);
    }

    public static int da() {
        int i = 0;
        try {
            return i;
        } finally {
            i++;
        }
    }
}
