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
import java.util.ArrayList;

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
    
    @Test
    public void testGetExternalOfSingleNode(){
        System.out.println("gets all edges of the single node");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        g.addEdge(1, 4, 1);
        g.addEdge(4, 5, 3);
        g.addEdge(2, 1, 7);
        g.addEdge(3, 1, 2);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 3);
        List<Integer> seed = new ArrayList();
        seed.add(1);
        GraphMST res = g.getExternal(seed);
        assertEquals(res.getEdgeNumber(), 4);
        assertTrue(res.edgeExists(1, 5));
        assertTrue(res.edgeExists(1, 4));
        assertTrue(res.edgeExists(1, 3));
        assertTrue(res.edgeExists(1, 2));
        
        assertTrue(res.getEdgeWeight(1, 5) == 2);
        assertTrue(res.getEdgeWeight(1, 4) == 1);
        assertTrue(res.getEdgeWeight(1, 3) == 2);
        assertTrue(res.getEdgeWeight(1, 2) == 7);

    }

    @Test
    public void testGetExternalOfTwoNodes() {
        System.out.println("get external edges of two nodes");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        g.addEdge(1, 4, 1);
        g.addEdge(4, 5, 3);
        g.addEdge(2, 1, 7);
        g.addEdge(3, 1, 2);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 3);
        List<Integer> seed = new ArrayList();
        seed.add(1);
        seed.add(4);
        GraphMST res = g.getExternal(seed);
        assertEquals(res.getEdgeNumber(), 5);
        assertTrue(res.edgeExists(1, 5));
        assertTrue(res.edgeExists(1, 3));
        assertTrue(res.edgeExists(1, 2));

        assertTrue(res.getEdgeWeight(1, 5) == 2);
        assertTrue(res.getEdgeWeight(1, 3) == 2);
        assertTrue(res.getEdgeWeight(1, 2) == 7);
        
        assertTrue(res.edgeExists(4, 5));
        assertTrue(res.edgeExists(4, 2));

        assertTrue(res.getEdgeWeight(4, 5) == 3);
        assertTrue(res.getEdgeWeight(4, 2) == 3);
    }
    
    @Test
    public void testGetExternalOfThreeNodes() {
        System.out.println("get external edges of three nodes");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        g.addEdge(1, 4, 1);
        g.addEdge(4, 5, 3);
        g.addEdge(2, 1, 7);
        g.addEdge(3, 1, 2);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 3);
        List<Integer> seed = new ArrayList();
        seed.add(1);
        seed.add(4);
        seed.add(5);
        GraphMST res = g.getExternal(seed);
        assertEquals(res.getEdgeNumber(), 3);
        assertTrue(res.edgeExists(1, 3));
        assertTrue(res.edgeExists(1, 2));
        assertTrue(res.edgeExists(4, 2));
        assertTrue(res.getEdgeWeight(1, 3) == 2);
        assertTrue(res.getEdgeWeight(1, 2) == 7);
        assertTrue(res.getEdgeWeight(4, 2) == 3);
    }
    
    
    @Test
    public void testGetCheapestEdgeIfSingle()
    {
        System.out.println("Return the only edge of the graph");
        GraphMST g = new GraphMST();
        g.addEdge(3, 8, 2);
        GraphMST g2 = g.cheapestEdge();
        assertTrue(g2.getEdgeNumber() == 1);
        assertTrue(g2.edgeExists(3, 8));
        assertTrue(g2.getEdgeWeight(3, 8) == 2);

    }
    
    
    @Test
    public void testGetCheapestEdgeIfTwoEdges() {
        System.out.println("Return the the cheapest of two edges.");
        GraphMST g = new GraphMST();
        g.addEdge(3, 8, 2);
        g.addEdge(2, 6, -5);
        GraphMST g2 = g.cheapestEdge();
        assertTrue(g2.getEdgeNumber() == 1);
        assertTrue(g2.edgeExists(2, 6));
        assertTrue(g2.getEdgeWeight(2, 6) == -5);
    }
    
    @Test
    public void testGetCheapestEdgeIfSevenEdges() {
        System.out.println("Return one of the the cheapest edges among seven-edge graph.");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        g.addEdge(1, 4, 1);
        g.addEdge(4, 5, 3);
        g.addEdge(2, 1, 7);
        g.addEdge(3, 1, 2);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 3);
        GraphMST g2 = g.cheapestEdge();
        assertTrue(g2.getEdgeNumber() == 1);
        assertTrue(g2.edgeExists(1, 4) ||  g2.edgeExists(2, 3));
//        System.out.println(g2.getEdges());
//        assertTrue(g2.getEdgeWeight(2, 6) == -5);
    }
    
    @Test
    public void testGetFirstEdgeEmptyGraph()
    {
        System.out.println("Return array with un-initialized values if graph is empty.");
        GraphMST g = new GraphMST();
        Integer[] edge = g.getFirstEdge();
        assertTrue(edge[0] == null);
        assertTrue(edge[1] == null);
        assertTrue(edge[2] == null);
    }
    
    @Test
    public void testGetFirstEdge() 
    {
        System.out.println("Return info about the edge of single edge graph");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        Integer[] edge = g.getFirstEdge();
        assertTrue(edge[0] == 1);
        assertTrue(edge[1] == 5);
        assertTrue(edge[2] == 2);
    }
    
    @Test
    public void testGetFirstEdgeTwoEdges() 
    {
        System.out.println("Return info about the edge of two edge graph");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        g.addEdge(4, 3, 1);
        Integer[] edge = g.getFirstEdge();
        assertTrue(
                (
                    ((edge[0] == 1 && edge[1] == 5)  ||  (edge[0] == 5 && edge[1] == 1) ) 
                        &&
                     edge[2] == 2
                ) 
                ||
                ( 
                    ((edge[0] == 4 && edge[1] == 3) || (edge[0] == 3 && edge[1] == 4) )
                        && 
                    edge[2] == 1
                ) 
            );
    }
    
    
    @Test
    public void testGetTotalWeightEmpty()
    {
        System.out.println("Returns null if graph is empty");
        GraphMST g = new GraphMST();
        assertTrue(g.getTotalWeight() == null);
    }

    
    @Test
    public void testGetTotalWeightSingleEdge() 
    {
        System.out.println("Returns edge weigth if graph has only that edge");
        GraphMST g = new GraphMST();
        g.addEdge(1, 6, 10);
        assertTrue(g.getTotalWeight() == 10);
    }
    
    @Test
    public void testGetTotalWeightFiveEdges() 
    {
        System.out.println("Returns sum of edge weigths");
        GraphMST g = new GraphMST();
        g.addEdge(1, 6, 10);
        g.addEdge(3, 6, 3);
        g.addEdge(2, 3, -4);
        g.addEdge(2, 6, 2);
        g.addEdge(6, 4, 6);
        assertTrue(g.getTotalWeight() == 10 + 3 - 4 + 2 + 6);
    }
    
    @Test
    public void testGetMSTEmpty()
    {
        System.out.println("Returns empty graph if target graph is empty");
        GraphMST g = new GraphMST();
        GraphMST mst = g.getMST();
        assertTrue(mst.getEdgeNumber() == 0);
        assertTrue(mst.getNodeNumber() == 0);
    }

    
    @Test
    public void testGetMSTSingle() {
        System.out.println("Returns single node graph if target graph has just one edge");
        GraphMST g = new GraphMST();
        g.addEdge(3, 2, 8);
        GraphMST mst = g.getMST();
        assertTrue(mst.getEdgeNumber() == 1);
        assertTrue(mst.getNodeNumber() == 2);
        assertTrue(mst.edgeExists(3, 2));
        assertTrue(mst.getTotalWeight() == 8);
    }

    
    @Test
    public void testGetMSTSeven() {
        System.out.println("Returns mst of graph with seven edges");
        GraphMST g = new GraphMST();
        g.addEdge(1, 5, 2);
        g.addEdge(1, 4, 1);
        g.addEdge(4, 5, 3);
        g.addEdge(2, 1, 7);
        g.addEdge(3, 1, 2);
        g.addEdge(2, 3, 1);
        g.addEdge(2, 4, 3);

        
        GraphMST mst = g.getMST();
        assertTrue(mst.getEdgeNumber() == 4);
        assertTrue(mst.getNodeNumber() == 5);
        assertTrue(mst.edgeExists(1, 3));
        assertTrue(mst.edgeExists(1, 5));
        assertTrue(mst.edgeExists(1, 4));
        assertTrue(mst.edgeExists(2, 3));
        assertTrue(mst.getTotalWeight() == 6);
    }


}
