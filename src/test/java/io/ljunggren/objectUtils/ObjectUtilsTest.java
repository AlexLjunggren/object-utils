package io.ljunggren.objectUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class ObjectUtilsTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class User {
        private String name;
        private int age;
        private boolean active;
    }
    
    @Test
    public void instantiationTest() {
        assertNotNull(new ObjectUtils());
    }
    
    @Test
    public void trimPropertiesTest() throws IllegalArgumentException, IllegalAccessException  {
        User user = new User("  Alex  ", 40, true);
        user = ObjectUtils.trimProperties(user);
        assertEquals("Alex", user.getName());
    }

    @Test
    public void trimPropertiesPrimitiveTest() throws IllegalArgumentException, IllegalAccessException {
        String name = "  Alex  ";
        name = ObjectUtils.trimProperties(name);
        assertEquals("Alex", name);
    }
    
    @Test
    public void trimPropertiesNullTest() throws IllegalArgumentException, IllegalAccessException {
        Object object = ObjectUtils.trimProperties(null);
        assertNull(object);
    }
    
    @Data
    private static class FinalUser {
        private final String name = "  Alex  ";
    }
    
    @Test
    public void trimPropertiesFinalTest() throws IllegalArgumentException, IllegalAccessException {
        FinalUser user = new FinalUser();
        user = ObjectUtils.trimProperties(user);
        assertEquals("  Alex  ", user.getName());
    }
    
}
