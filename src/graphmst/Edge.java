/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graphmst;

/**
 *
 * @author Andrea
 */
public class Edge {
    /**
     * Number of first end
     */
    private int _first;

    /**
     * Number of second end
     */
    private int _second;

    /**
     * Edge weight
     */
    private int _weight;

    /**
     * weight getter
     *
     * @return
     */
    public int getWeight() {
        return _weight;
    }

    /**
     * weight setter
     *
     * @return
     */
    public void setWeight(int _weight) {
        this._weight = _weight;
    }

    /**
     * Creates undirected edge connecting nodes _first and _second
     *
     * @param _first
     * @param _second
     * @param _weight
     */
    public Edge(int _first, int _second, int _weight) {
        if (_first > _second) {
            this._first = _second;
            this._second = _first;
        } else {
            this._first = _first;
            this._second = _second;
        }
        this._weight = _weight;
    }

    /**
     * _first getter
     *
     * @return int
     */
    public int firstEnd() {
        return this._first;
    }

    /**
     * _second getter
     *
     * @return int
     */
    public int secondEnd() {
        return this._second;
    }

    /**
     * Returns array containing numbers of the edge ends
     *
     * @return
     */
    public Integer[] getEnds() {
        Integer[] ends = new Integer[2];
        ends[0] = this._first;
        ends[1] = this._second;
        return ends;
    }

    /**
     * Returns true if the edge connects given nodes, false otherwise
     *
     * @param end1
     * @param end2
     * @return boolean
     */
    public boolean connects(int end1, int end2) {
        return (this._first == end1 && this._second == end2) || (this._first == end2 && this._second == end1);
    }


}
