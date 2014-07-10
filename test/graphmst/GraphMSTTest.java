/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphmst;

import java.util.HashMap;
import java.util.List;
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
public class GraphMSTTest {
    
    
    public GraphMSTTest() {
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
     * Test of getNodeNumber method, of class GraphMST.
     */
    @Test
    public void testGetNodeNumber0() {
        System.out.println("gets zero node number for empty graph");
        GraphMST g = new GraphMST();
        assertEquals(g.getNodeNumber(), 0);
    }

    
    /**
     * Test of getNodeNumber method, of class GraphMST.
     */
    @Test
    public void testGetNodeNumber1() {
        System.out.println("gets 3 for graph with three nodes");
        GraphMST g = new GraphMST();
        g.addEdge(1, 2, 4);
        g.addEdge(4, 2, 5);
        assertEquals(g.getNodeNumber(), 3);
    }
    
    /**
     * Test of getNodeNumber method, of class GraphMST.
     */
    @Test
    public void testGetNodeNumber2() {
        System.out.println("gets 5 for graph with five nodes");
        GraphMST g = new GraphMST();
        g.addEdge(1, 2, 4);
        g.addEdge(4, 2, 5);
        g.addEdge(3, 1, 1);
        g.addEdge(5, 2, -8);
        assertEquals(g.getNodeNumber(), 5);
        System.out.println(g.getEdges());
    }
    
    @Test
    public void testGetNodeNumber3() {
        System.out.println("gets 4 for graph with four nodes and two edges");
        GraphMST g = new GraphMST();
        g.addEdge(1, 2, 4);
        g.addEdge(4, 3, 5);
        assertEquals(g.getNodeNumber(), 4);
    }

    /**
     * Test of addEdge method, of class GraphMST.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddEdgeTwice() {
        System.out.println("add edge that already is present");
        GraphMST g = new GraphMST();
        g.addEdge(3, 5, -1);
        g.addEdge(3, 5, 6);
    }

    /**
     * Test of main method, of class GraphMST.
     */
    @Test
    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        GraphMST.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }


    /**
     * Test of getEdgeNumber method, of class GraphMST.
     */
    @Test
    public void testGetEdgeNumberEmptyGraph() {
        System.out.println("returns 0 edge numbers for empty graph");
        GraphMST g = new GraphMST();
        assertEquals(g.getEdgeNumber(), 0);
    }

    @Test
    public void testGetEdgeNumber3Edges() {
        System.out.println("returns 3 as edge numbers for graph with three "
                + "edges and six nodes");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 8);
        g.addEdge(3, 4, 2);
        g.addEdge(7, 8, -61);
        assertEquals(g.getEdgeNumber(), 3);
    }



    /**
     * Test of edgeExists method, of class GraphMST.
     */
    @Test
    public void testEdgeExistsEmptyGraph() {
        System.out.println("returns false if graph is empty");
        GraphMST g = new GraphMST();
        assertFalse(g.edgeExists(3, 1));
    }

    
    @Test
    public void testEdgeExistsNoSuchEdge() {
        System.out.println("returns false if there is no such edge in the graph.");
        GraphMST g = new GraphMST();
        g.addEdge(2, 1, 4);
        g.addEdge(4, 3, 1);
        assertFalse(g.edgeExists(4, 2));
    }
    
    @Test
    public void testEdgeExistsEdgeExists() {
        System.out.println("returns true if the edge exists");
        GraphMST g = new GraphMST();
        g.addEdge(2, 1, 4);
        g.addEdge(4, 3, 1);
        assertTrue(g.edgeExists(3, 4));
    }

    
}
