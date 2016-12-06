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
        double distance = 0.0;
        if (first != null) {
            Node current = first;
            do {
                distance += current.p.distanceTo(current.next.p);
                current = current.next;
            } while (current != first);
        }
        return distance;
    }

    public void insertNearest(Point p) {}

    public void insertSmallest(Point p) {}

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
        squareTour.show();
        StdOut.println(squareTour.distance());

        StdDraw.setXscale(0, 600);
        StdDraw.setYscale(0, 600);
        squareTour.draw();
    }

    public void insertInOrder(Point p) {
        //TODO:
        /*
        Sam, hier moet je de opdracht van de eerst bullet uitvoeren.
        Het punt p toevoegen aan het einde van de "linked list".
        Let hierbij op het volgende:
        Bij het toevoegen van het eerste punt is first gelijk aan null.
        Hier moet je iets anders doen dan bij het toevoegen van de andere punten.
         */
        if (first == null) {
            // hier moet je het aanmaken van de eerste Node doen en toekennen aan first.
            // De next van deze node moet verwijzen naar first (een cirkel met 1 punt)
        } else {
            // Hier eerst het laatste punt in de linkedlist vinden.
            // Vervolgens een nieuwe Node maken voor het nieuwe punt.
            // Daarna "next" uit het laatste punt naar deze nieuwe node laten verwijzen
            // Daarna "next" van jouw nieuwe node laten verwijzen naar first om
            // de cyclus weer dicht te maken
        }
    }
}
