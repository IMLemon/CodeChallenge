public class ImmutableQueue<T> implements Queue<T> {

    private class ImmutableStack {
        private final T head;
        private final ImmutableStack tail;

        public ImmutableStack() {
            this.head = null;
            this.tail = null;
        }

        private ImmutableStack(T head, ImmutableStack tail) {
            this.head = head;
            this.tail = tail;
        }

        public ImmutableStack push(T t) {
            return new ImmutableStack(t, this);
        }

        public ImmutableStack pop() {
            return this.tail;
        }

        public T peek() {
            return this.head;
        }

        public boolean isEmpty() {
            return this.head == null && this.tail == null;
        }

        public ImmutableStack reverse() {
            ImmutableStack result = new ImmutableStack();
            ImmutableStack tmp = this;
            while (!tmp.isEmpty()) {
                result = result.push(tmp.peek());
                tmp = tmp.pop();
            }
            return result;
        }
    }

    private final ImmutableStack front; // stack to remove elements from
    private final ImmutableStack back; // stack to add elements to

    public ImmutableQueue() { // empty queue using two empty stacks
        this.front = new ImmutableStack();
        this.back = new ImmutableStack();
    }

    private ImmutableQueue(ImmutableStack front, ImmutableStack back) {
        this.front = front;
        this.back = back;
    }

    @Override
    public Queue<T> enQueue(T t) {
        if (this.front.isEmpty()) { // always ensure front is not empty, if it is, reverse the "back" stack and add new element to it.
            return new ImmutableQueue<T>(this.back.reverse().push(t), new ImmutableStack());
        }
        return new ImmutableQueue<T>(this.front, this.back.push(t));
    }

    @Override
    public Queue<T> deQueue() {
        if (this.front.isEmpty()) { // if front is empty reverse the back, remove its top and set it as front stack.
            return new ImmutableQueue<T>(this.back.reverse().pop(), new ImmutableStack());
        } else {
            ImmutableStack newFront = this.front.pop();
            if (newFront.isEmpty()) { // ensure new front stack is not empty after removing the element
                return new ImmutableQueue<T>(this.back.reverse(), new ImmutableStack());
            }
            return new ImmutableQueue<T>(newFront, this.back);
        }
    }

    @Override
    public T head() {
        return this.front.peek();
    }

    @Override
    public boolean isEmpty() {
        return this.front.isEmpty() && this.back.isEmpty();
    }
}