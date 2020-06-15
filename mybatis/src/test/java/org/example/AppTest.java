package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test(){
        
    }

    static class  MyClassLoader extends  URLClassLoader{

        public MyClassLoader(URL[] urls, ClassLoader parent) {
            super(urls, parent);
        }
    }

}
