public class Test {
    public static void main(String[] args) {
        String a = "abcd";


        System.out.println(a.hashCode());
//      s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
   /* public int hashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }*/
//    String ="abcd";
//    h = 31*0 + a
//    h = 31*(31*0 + a) +b
//    h = 31*(31*(31*0 + a) +b) + c
//    h = 31*(31*(31*a +b) + c) + d
//    h = 31^3*a +31^2*b + 31*1*C + d = 31^3*97 +31^2*98 + 31*1*99 + 100 =2987074(ASCII)
//    h = s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]

    }
}
