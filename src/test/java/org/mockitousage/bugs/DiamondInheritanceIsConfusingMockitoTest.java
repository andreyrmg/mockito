package org.mockitousage.bugs;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

// see #508
public class DiamondInheritanceIsConfusingMockitoTest {

    @Test
    public void should_work() {
        Sub mock = Mockito.mock(Sub.class);
        // The following line results in
        // org.mockito.exceptions.misusing.MissingMethodInvocationException:
        // when() requires an argument which has to be 'a method call on a mock'.
        // Presumably confused by the interface/superclass signatures.
        Mockito.when(mock.getFoo()).thenReturn("Hello");

        Assert.assertEquals("Hello", mock.getFoo());
    }

    public class Super<T> {
        private T value;

        public Super(T value) {
            this.value = value;
        }

        public T getFoo() { return value; }
    }

    public class Sub
            extends Super<String>
            implements iInterface {

        public Sub(String s) {
            super(s);
        }
    }

    public interface iInterface {
        String getFoo();
    }
}