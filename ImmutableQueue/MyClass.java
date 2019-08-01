public class MyClass {
    public static void main(String args[]) {
        Queue<Integer> q = new ImmutableQueue<Integer>();
        int a = 1;
        System.out.println(q.isEmpty());
        q = q.enQueue(a);
        System.out.println(q.isEmpty());
        q = q.deQueue();
        System.out.println(q.isEmpty());
    }
}