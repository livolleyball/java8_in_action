import java.util.*;

public class udfPmax {
    public static int max(int a, int b) {
        int reslut;
        if (a > b) {
            reslut = a;
        } else {
            reslut = b;
        }
        System.out.println("max:"+reslut);
        return reslut; // 结束方法的运行，并返回值
    }

    public static void main(String[] args) {
        int i = 5;
        int j = 10;
        int k = max(i, j);
        System.out.println(k);
        udfPmax tm = new udfPmax();
        tm.printMethod();
        tm.min(20, 30);
        tm.max(20, 30);
        tm.printMax(34, 3, 3, 2, 56.5);
        String str =(String) null;
        ArrayList a=new ArrayList<>();
        a.add('a');
        a.add('b');
        a.add('d');
        a.add('c');

        tm.anyNone(a);

    }

    void printMethod() {
        System.out.println("这是一个通过类访问的方法");
    }

    int min(int a, int b) {
        int reslut;
        if (a > b) {
            reslut = b;
        } else {
            reslut = a;
        }
        System.out.println("min:"+reslut);
        return reslut;
    }

    //    可变参数
    public static void printMax( double... numbers) {
        if (numbers.length == 0) {
            System.out.println("No argument passed");
            return;
        }

        double result = numbers[0];

        for (int i = 1; i <  numbers.length; i++){
            if (numbers[i] >  result) {
                result = numbers[i];
            }
        }
        System.out.println("The max value is " + result);
    }

    public static boolean anyNone(ArrayList  args) {
        if (args.isEmpty()) {
            System.out.println("No argument passed");
            return true;
        }

        boolean result = false;

        for (int i = 1; i <  args.size(); i++){
            if (args.get(i) == null  ) {
                result = true;
                break;
            }
        }
        System.out.println("The max value is " + result);
        return result;
    }
}
