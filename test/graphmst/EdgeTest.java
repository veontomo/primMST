/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphmst;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrea
 */
public class EdgeTest {
    
    public EdgeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight() {
        System.out.println("get weight");
        Edge e = new Edge(1, 2, 6);
        assertEquals(e.getWeight(), 6);
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setting weight");
        Edge e = new Edge(3, 1, 4);
        e.setWeight(7);
        assertEquals(e.getWeight(), 7);
    }
    
    @Test
    public void testCreateEdge() {
        System.out.println("creating edge if nodes are in increasing order");
        Edge e = new Edge(2, 5, 4);
        Integer[] ends = e.getEnds();
        assertTrue(ends[0] == 2);
        assertTrue(ends[1] == 5);
   }
    
    
    @Test
    public void testCreateEdge2() {
        System.out.println("creating edge if nodes are in decreasing order");
        Edge e = new Edge(6, 1, 3);
        Integer[] ends = e.getEnds();
        assertTrue(ends[0] == 1);
        assertTrue(ends[1] == 6);
    }
    
    @Test
    public void testConnects() {
        System.out.println("returns true if it connects the ends");
        Edge e = new Edge(6, 1, 3);
        assertTrue(e.connects(6, 1));
        assertTrue(e.connects(1, 6));

    }
    
    @Test
    public void testConnects2() {
        System.out.println("returns false if one end is wrong");
        Edge e = new Edge(5, 4, 2);
        assertFalse(e.connects(5, 1));
        assertFalse(e.connects(4, 2));
        assertFalse(e.connects(4, 4));
        assertFalse(e.connects(2, 2));

    }


} 