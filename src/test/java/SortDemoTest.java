package test.java;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.Item;
import main.java.SortAlgos;
import main.java.SortDemoData;

class SortDemoTest {

    SortDemoData data = new SortDemoData();


    /**
    * testNumber checks that the array size given is correct
    */
    @Test
    public void testNumber() {
       data.initializeArray("20");
       assertTrue(data.myArray.length == 20);
       
       data.initializeArray("30");
       assertTrue(data.myArray.length == 30);
       
       data.initializeArray("0");
       assertTrue(data.myArray.length == 0);
    }


    /**
     * negativeArraySize checks that the correct exception is thrown for a negative array size
     */
    @Test
    public void negativeArraySize() {
        assertThrows(NegativeArraySizeException.class,
                ()->{
                    data.initializeArray("-3"); 
                });
    }
    
    /**
     * NonIntegerValue checks that the correct exception is thrown when a non integer value is given
     */
    @Test
    public void NonIntegerValue() {
        assertThrows(NumberFormatException.class,
                ()->{
                    data.initializeArray("5.5 7 9");
                });
    }
    
    /**
     * NoSuchElem checks that the correct exception is thrown for an empty array
     */
    @Test
    public void NoSuchElem() {
        assertThrows(java.util.NoSuchElementException.class,
                ()->{
                    data.initializeArray("");
                }); 
    }
    
    /**
     * NoNumber checks that the correct exception is thrown if a character is given
     */
    @Test
    public void NoNumber() {
        assertThrows(NumberFormatException.class,
                ()->{
                    data.initializeArray("5 8 y 4"); 
                }); 
         
    }
    
    /**
     * SameNumber checks that duplicate numbers are removed
     */
    // @Test
    // public void SameNumber() {
    //     data.initializeArray("5 5 64 4 4");  
        
    //     SortDemoData data2 = new SortDemoData();
    //     data2.initializeArray("5 64 4");
    //     assertTrue(data.myArray.length == 3);
    //     assertTrue(data.myArray[0].key == data2.myArray[0].key );
    //     assertTrue(data.myArray[1].key == data2.myArray[1].key );
    //     assertTrue(data.myArray[2].key == data2.myArray[2].key );
    // }
    
}