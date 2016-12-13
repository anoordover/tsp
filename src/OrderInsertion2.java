import java.util.ArrayList;
import java.util.List;

/*************************************************************************
 *  YOU DO NOT NEED TO MODIFY THIS FILE
 *
 *  Compilation:  javac OrderInsertion.java
 *  Execution:    java OrderInsertion < file.txt
 *  Dependencies: Tour.java Point.java StdIn.java StdDraw.java
 *
 *  Run order of input insertion heuristic for traveling
 *  salesperson problem and plot results.
 *
 *  % java OrderInsertion < tsp10.txt
 *
 *************************************************************************/

public class OrderInsertion2 {

    public static void main(String[] args) {

        // get dimensions
        In in = new In("data/tsp100.txt");
        int w = in.readInt();
        int h = in.readInt();
        StdDraw.setCanvasSize(w, h);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);

        // turn on animation mode
        StdDraw.show(0);

        List<Point> points = new ArrayList<>();
        // run smallest insertion heuristic
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point p = new Point(x, y);
            points.add(p);
        }

        Tour tour = new Tour();
        Point previous = points.get(0);
        tour.insertInOrder(previous);
        points.remove(0);

        while (!points.isEmpty()) {
            double minDistance = Double.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < points.size(); i++) {
                if (previous.distanceTo(points.get(i)) < minDistance) {
                    minDistance = previous.distanceTo(points.get(i));
                    minIndex = i;
                }
            }
            previous = points.get(minIndex);
            points.remove(minIndex);
            tour.insertInOrder(previous);
            StdDraw.clear();
            tour.draw();
            StdDraw.text(100, 10, "" + tour.distance());
            StdDraw.show(50);
        }

        // draw to standard draw
        tour.draw();
        StdDraw.show(0);
        
        // print tour to standard output
        StdOut.printf("Tour distance =  %.4f\n", tour.distance());
        StdOut.printf("Number of points = %d\n", tour.size());
        tour.show();
/*
        Node current = tour.first;
        boolean swap = true;
        while (swap) {
            swap = false;
            while (current.next.next != tour.first) {
                Node inner = current.next.next;
                while (inner != tour.first) {
                    if (tour.deltaForSwap(current, inner) > 0.0) {
                        StdDraw.circle(current.p.x, current.p.y, 5.0);
                        StdDraw.circle(inner.p.x, inner.p.y, 5.0);
                        StdDraw.show(50);
                        tour.swap(current, inner);
                        swap = true;
                    }
                    StdDraw.clear();
                    tour.draw();
                    StdDraw.text(100, 0, "" + tour.distance());
                    StdDraw.show(50);
                    inner = inner.next;
                }
                current = current.next;
            }

        }

        // print tour to standard output
        StdOut.printf("Tour distance =  %.4f\n", tour.distance());
        StdOut.printf("Number of points = %d\n", tour.size());
        tour.show();
*/
    }

}

