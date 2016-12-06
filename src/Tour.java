/**
 * Created by snoordover on 6-12-16.
 */
public class Tour {

    Node first;

    public Tour() {

    }

    public Tour(Point a, Point b, Point c, Point d) {
        Node nodeA = new Node();
        Node nodeB = new Node();
        Node nodeC = new Node();
        Node nodeD = new Node();
        nodeA.p = a;
        nodeB.p = b;
        nodeC.p = c;
        nodeD.p = d;
        nodeA.next = nodeB;
        nodeB.next = nodeC;
        nodeC.next = nodeD;
        nodeD.next = nodeA;
        first = nodeA;
    }

    public void show() {

    }

    public void draw() {

    }

    public int size() {
        return 1;
    }

    public double distance() {
        return 0.0;
    }

    public void insertNearest(Point p) {}

    public void insertSmallest(Point p) {}
}
