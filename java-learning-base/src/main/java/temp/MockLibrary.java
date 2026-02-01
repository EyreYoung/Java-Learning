package temp;

import java.lang.reflect.*;
import java.util.*;

interface TestInterface {
    int calculate(int a, int b);
    String calculate(String a, String b);
    String getValue();
}

public class MockLibrary implements InvocationHandler {

    private class MockKey {
        private String methodName;

        private Object[] args;

        public MockKey(String m, Object[] a) {
            this.methodName = m;
            this.args = a;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(methodName) * 31 + Arrays.deepHashCode(args);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || o.getClass() != getClass()) {
                return false;
            }
            return methodName.equals(((MockKey) o).methodName)
                    && Arrays.deepEquals(args, ((MockKey) o).args);
        }
    }

    private Map<MockKey, Object> map = new HashMap<>();
    private MockKey key;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MockKey mockKey = new MockKey(method.getName(), args);
        if (map.containsKey(mockKey)) {
            return map.get(mockKey);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public MockLibrary when(String method, Object[] args) {
        key = new MockKey(method, args);
        return this;
    }

    public void thenReturn(Object val) {
       if (key == null) {
           throw new IllegalArgumentException();
       }
       map.put(key, val);
       key = null;
    }

    public static void main(String[] args) {
        MockLibrary handler = new MockLibrary();
        TestInterface ref = (TestInterface) Proxy.newProxyInstance(
                MockLibrary.class.getClassLoader(),
                new Class[]{TestInterface.class}, handler);

        handler.when("calculate", new Object[] {1, 2}).thenReturn(3);
        handler.when("calculate", new Object[] {"a", "b"}).thenReturn("ab");

        System.out.println(ref.calculate(1, 2)); // prints 3
        System.out.println(ref.calculate("a", "b")); // prints "ab"
    }
}