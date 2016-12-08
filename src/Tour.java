/**
 * Created by snoordover on 6-12-16.
 */
public class Tour {

    Node first;
    double distance;

    public Tour() {

    }

    public Tour(Point a, Point b, Point c, Point d) {
        Node nodeA = new Node(a);
        Node nodeB = new Node(b);
        Node nodeC = new Node(c);
        Node nodeD = new Node(d);
        addNodeAfter(null, nodeA);
        addNodeAfter(nodeA, nodeB);
        addNodeAfter(nodeB, nodeC);
        addNodeAfter(nodeC, nodeD);
    }

    private void addNodeAfter(Node after, Node insert) {
        if (after == null) {
            insert.next = insert;
            first = insert;
        } else {
            distance = distance - after.p.distanceTo(after.next.p);
            insert.next = after.next;
            after.next = insert;
            distance = distance + after.p.distanceTo(insert.p) + insert.p.distanceTo(insert.next.p);
        }
    }

    public void show() {
        //Als je helemaal geen eerst punt hebt kan je niets afdrukken
        if (first != null) {
            //Bijhouden waar je bent bij het afdrukken
            Node current = first;
            do {
                StdOut.println(current.p.toString());
                //Naar het volgende punt gaan in de route
                current = current.next;
                //Als je weer bij de eerste bent uitgekomen moet je stoppen
            } while (current != first);
        }
    }

    public void draw() {
        //Als je helemaal geen eerst punt hebt kan je niets afdrukken
        if (first != null) {
            //Bijhouden waar je bent bij het afdrukken
            Node current = first;
            do {
                current.p.drawTo(current.next.p);
                //Naar het volgende punt gaan in de route
                current = current.next;
                //Als je weer bij de eerste bent uitgekomen moet je stoppen
            } while (current != first);
        }
    }

    public int size() {
        //Als je helemaal geen eerst punt hebt is de "size" 0
        if (first == null) {
            return 0;
        }
        int i = 0;
        //Zie voor de rest commentaar bij "show"
        Node current = first;
        do {
            i++;
            current = current.next;
        } while (current != first);
        return i;
    }

    public double distance() {
        return distance;
    }

    public void insertNearest(Point p) {
        addNodeAfter(getNearest(p), new Node(p));
    }

    private Node getNearest(Point p) {
        if (first == null) {
            return null;
        }
        Node current = first;
        Node found = first;
        double minDistance = Double.MAX_VALUE;
        do {
            if (current.p.distanceTo(p) < minDistance) {
                found = current;
                minDistance = current.p.distanceTo(p);
            }
            current = current.next;
        } while (current != first);
        return found;
    }

    public void insertSmallest(Point p) {
        addNodeAfter(getSmallest(p), new Node(p));
    }

    private Node getSmallest(Point p) {
        if (first == null){
            return null;
        }
        Node current = first;
        Node found = first;
        double minDistance = Double.MAX_VALUE;
        do {
            if (current.p.distanceTo(p) + p.distanceTo(current.next.p) - current.p.distanceTo(current.next.p) < minDistance) {
                minDistance = current.p.distanceTo(p) + p.distanceTo(current.next.p) - current.p.distanceTo(current.next.p);
                found = current;
            }
            current = current.next;
        } while (current != first);
        return found;
    }

    // main method for testing
    public static void main(String[] args) {
        // define 4 points forming a square
        Point a = new Point(100.0, 100.0);
        Point b = new Point(500.0, 100.0);
        Point c = new Point(500.0, 500.0);
        Point d = new Point(100.0, 500.0);

        // Set up a Tour with those four points
        // The constructor should link a->b->c->d->a
        Tour squareTour = new Tour(a, b, c, d);

        // Output the Tour
        squareTour.swap(squareTour.first, squareTour.first.next.next);
        squareTour.show();
        StdOut.println(squareTour.distance());
        StdOut.println(squareTour.deltaForSwap(squareTour.first, squareTour.first.next.next));

        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        squareTour.draw();
    }

    public void insertInOrder(Point p) {
        if (first == null) {
            addNodeAfter(null, new Node(p));
        } else {
            addNodeAfter(getLastNode(), new Node(p));
        }
    }

    private Node getLastNode() {
        if (first == null) {
            return null;
        }
        Node current = first;
        do {
            current = current.next;
        } while (current.next != first);
        return current;
    }

    public double deltaForSwap(Node from, Node to) {
        double currentDistance = from.p.distanceTo(from.next.p) + to.p.distanceTo(to.next.p);
        double newDistance = from.p.distanceTo(to.p) + to.next.p.distanceTo(from.next.p);
        return currentDistance - newDistance;
    }

    public void swap(Node from, Node to) {
        distance = distance - deltaForSwap(from, to);
        if (from.next == to) {
            return;
        }
        Node beginRoute = from;
        Node endRoute = to.next;
        Node ingang = from.next;
        Node node1 = from.next;
        Node node2 = node1.next;
        Node node3 = node2.next;
        do {
            node2.next = node1;
            node1 = node2;
            node2 = node3;
            node3 = node3.next;
        } while (node1 != to);
        ingang.next = endRoute;
        beginRoute.next = to;
    }
}
