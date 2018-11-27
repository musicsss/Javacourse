/**
 * @author：wangyulin
 * 16130120138
 * mail:1009948721@qq.com
 */
package www.wyl.the_second;

public class SubString {
    static int j=0;
    private static int a;
    private static int b;

    public static void main(String[] args) {
        if(args[1]!=null){
            a = Integer.parseInt(args[1]);
        }
        if(args[2]!=null){
            b = Integer.parseInt(args[2]);
        }
        if(b<a){
            return ;
        }
        String tes = args[0];
        if(tes.length()<b+1){
            System.out.println("WARN:the String length smaller than your input!");
            System.out.println(tes.substring(a,tes.length()));
            return;
        }
        System.out.printf(tes.substring(a,b));
    }
}
