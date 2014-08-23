/*
 * Created on 05-mar-2005
 *
 */
package util;

import java.awt.*;

import weapons.Explossion;

/**
 * @author David Toro
 * 
 */
public class Fractal {

    private int numberOfPoints;

    private Polygon polygon;


    /**
     * Constructor
     * 
     * @param polygon Es el polnhgono sobre el que se hace el fractal
     * @param numberOfPoints El nnhmero de puntos del nuevo polnhgono
     */
    public Fractal(Polygon polygon, int numberOfPoints,int type) {
        this.polygon = polygon;
        this.numberOfPoints = numberOfPoints;
        switch(type){
        case Explossion.FRACTAL_POLYGON:
          init();
          break;
        case Explossion.FRACTAL_POLYGON2:
          init2();
          break;
        case Explossion.FRACTAL_POLYGON3:
          init3();
          break;
        default:
          break;
        }
    }


    /**
     * Crea el fractal recorriendo todos los vnhrtices del polnhgono inicial Entre
     * cada 2 vnhrtices inserta uno nuevo que se obtiene utilizando el lnhpiz para
     * calcular su posicinhn con respecto a ellos.
     */
    private void init() {
        Point point = new Point();
        Pencil pencil = new Pencil(point, 0.0);
        int[] x = new int[this.numberOfPoints];
        int[] y = new int[this.numberOfPoints];
        for (int i = 0; i < polygon.npoints; i++) {
            double longside, angle;
            Point p1 = new Point();
            Point p2 = new Point();
            p1.setLocation(polygon.xpoints[i], polygon.ypoints[i]);
            if (i == (polygon.npoints - 1))
                p2.setLocation(polygon.xpoints[0], polygon.ypoints[0]);
            else
                p2.setLocation(polygon.xpoints[i + 1], polygon.ypoints[i + 1]);
            angle = ang(p1, p2);
            if (p1.getY() == p2.getY())
                longside = StrictMath.abs(p1.getX() - p2.getX());
            else
                longside = StrictMath.abs((p1.getY() - p2.getY())
                        / StrictMath.sin(angle * StrictMath.PI / 180));
            pencil.turnTo(angle);
            pencil.moveTo(p1);
            pencil.turn(45.0);
            pencil.nextStep((longside / (double) 2.0) / 0.707106781);
            Point p3 = new Point();
            p3.setLocation(pencil.getPosition());
            x[2 * i] = p1.x;
            y[2 * i] = p1.y;
            x[(2 * i) + 1] = p3.x;
            y[(2 * i) + 1] = p3.y;
            if (i != (polygon.npoints - 1)) {
                x[(2 * i) + 2] = p2.x;
                y[(2 * i) + 2] = p2.y;
            }
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 2.0) / 0.707106781);
        }
        this.polygon = new Polygon(x, y, numberOfPoints);
    }

    /**
     * EXPLOSION TIPO ENGRANAJE PARA ADENTRO
     * Crea el fractal recorriendo todos los vnhrtices del polnhgono inicial Entre
     * cada 2 vnhrtices inserta cuatro nuevos que se obtienen utilizando el lnhpiz para
     * calcular su posicinhn con respecto a ellos.
     */
    private void init2() {
        Point point = new Point();
        Pencil pencil = new Pencil(point, 0.0);
        int[] x = new int[this.numberOfPoints];
        int[] y = new int[this.numberOfPoints];
        for (int i = 0; i < polygon.npoints; i++) {
            double longside, angle;
            Point p1 = new Point();
            Point p2 = new Point();
            p1.setLocation(polygon.xpoints[i], polygon.ypoints[i]);
            if (i == (polygon.npoints - 1))
                p2.setLocation(polygon.xpoints[0], polygon.ypoints[0]);
            else
                p2.setLocation(polygon.xpoints[i + 1], polygon.ypoints[i + 1]);
            angle = ang(p1, p2);
            if (p1.getY() == p2.getY())
                longside = StrictMath.abs(p1.getX() - p2.getX());
            else
                longside = StrictMath.abs((p1.getY() - p2.getY())
                        / StrictMath.sin(angle * StrictMath.PI / 180));
            pencil.turnTo(angle);
            pencil.moveTo(p1);
            pencil.nextStep((longside / (double) 3.0));
            Point p3 = new Point();
            p3.setLocation(pencil.getPosition());
            x[5 * i] = p1.x;
            y[5 * i] = p1.y;
            //insertamos el primer vertice
            x[(5 * i) + 1] = p3.x;
            y[(5 * i) + 1] = p3.y;
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p4 = new Point();
            p4.setLocation(pencil.getPosition());
            //insertamos el segundo vertice
            x[(5 * i) + 2] = p4.x;
            y[(5 * i) + 2] = p4.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p5 = new Point();
            p5.setLocation(pencil.getPosition());
            //insertamos el tercer vertice
            x[(5 * i) + 3] = p5.x;
            y[(5 * i) + 3] = p5.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p6 = new Point();
            p6.setLocation(pencil.getPosition());
            //insertamos el cuarto vertice
            x[(5 * i) + 4] = p6.x;
            y[(5 * i) + 4] = p6.y;
            if (i != (polygon.npoints - 1)) {
                x[(5 * i) + 5] = p2.x;
                y[(5 * i) + 5] = p2.y;
            }
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
        }
        this.polygon = new Polygon(x, y, numberOfPoints);
    }

    /**
     * EXPLOSION TIPO ESTAS MUERTO (CON CRUCES) 
     * SE RESERVA PARA EL NIVEL MAS BRUTO DEL ARMA
     * Crea el fractal recorriendo todos los vnhrtices del polnhgono inicial Entre
     * cada 2 vnhrtices inserta tres nuevos que se obtienen utilizando el lnhpiz para
     * calcular su posicinhn con respecto a ellos.
     */
    private void init3() {
        Point point = new Point();
        Pencil pencil = new Pencil(point, 0.0);
        int[] x = new int[this.numberOfPoints];
        int[] y = new int[this.numberOfPoints];
        for (int i = 0; i < polygon.npoints; i++) {
            double longside, angle;
            Point p1 = new Point();
            Point p2 = new Point();
            p1.setLocation(polygon.xpoints[i], polygon.ypoints[i]);
            if (i == (polygon.npoints - 1))
                p2.setLocation(polygon.xpoints[0], polygon.ypoints[0]);
            else
                p2.setLocation(polygon.xpoints[i + 1], polygon.ypoints[i + 1]);
            angle = ang(p1, p2);
            if (p1.getY() == p2.getY())
                longside = StrictMath.abs(p1.getX() - p2.getX());
            else
                longside = StrictMath.abs((p1.getY() - p2.getY())
                        / StrictMath.sin(angle * StrictMath.PI / 180));
            pencil.turnTo(angle);
            pencil.moveTo(p1);
            pencil.nextStep(longside / (double) 3.0);
            Point p3 = new Point();
            p3.setLocation(pencil.getPosition());
            x[13 * i] = p1.x;
            y[13 * i] = p1.y;
            //insertamos el primer vertice
            x[(13 * i) + 1] = p3.x;
            y[(13 * i) + 1] = p3.y;
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p4 = new Point();
            p4.setLocation(pencil.getPosition());
            //insertamos el segundo vertice
            x[(13 * i) + 2] = p4.x;
            y[(13 * i) + 2] = p4.y;
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p5 = new Point();
            p5.setLocation(pencil.getPosition());
            //insertamos el tercer vertice
            x[(13 * i) + 3] = p5.x;
            y[(13 * i) + 3] = p5.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p6 = new Point();
            p6.setLocation(pencil.getPosition());
            //insertamos el cuarto vertice
            x[(13 * i) + 4] = p6.x;
            y[(13 * i) + 4] = p6.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p7 = new Point();
            p7.setLocation(pencil.getPosition());
            //insertamos el quinto vertice
            x[(13 * i) + 5] = p7.x;
            y[(13 * i) + 5] = p7.y;
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p8 = new Point();
            p8.setLocation(pencil.getPosition());
            //insertamos el sexto vertice
            x[(13 * i) + 6] = p8.x;
            y[(13 * i) + 6] = p8.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p9 = new Point();
            p9.setLocation(pencil.getPosition());
            //insertamos el septimo vertice
            x[(13 * i) + 7] = p9.x;
            y[(13 * i) + 7] = p9.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p10 = new Point();
            p10.setLocation(pencil.getPosition());
            //insertamos el octavo vertice
            x[(13 * i) + 8] = p10.x;
            y[(13 * i) + 8] = p10.y;
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p11 = new Point();
            p11.setLocation(pencil.getPosition());
            //insertamos el noveno vertice
            x[(13 * i) + 9] = p11.x;
            y[(13 * i) + 9] = p11.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p12 = new Point();
            p12.setLocation(pencil.getPosition());
            //insertamos el decimo vertice
            x[(13 * i) + 10] = p12.x;
            y[(13 * i) + 10] = p12.y;
            pencil.turn(90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p13 = new Point();
            p13.setLocation(pencil.getPosition());
            //insertamos el undecimo vertice
            x[(13 * i) + 11] = p13.x;
            y[(13 * i) + 11] = p13.y;
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
            Point p14 = new Point();
            p14.setLocation(pencil.getPosition());
            //insertamos el duodecimo vertice
            x[(13 * i) + 12] = p14.x;
            y[(13 * i) + 12] = p14.y;
            
            if (i != (polygon.npoints - 1)) {
                x[(13 * i) + 13] = p2.x;
                y[(13 * i) + 13] = p2.y;
            }
            pencil.turn(-90.0);
            pencil.nextStep((longside / (double) 3.0));
        }
        this.polygon = new Polygon(x, y, numberOfPoints);
    }
    
    /**
     * @return el polnhgono actual que representa la figura.
     */

    public Polygon getPolygon() {
        return this.polygon;
    }


    /**
     * 
     * @param p1 Primer punto
     * @param p2 Segundo punto
     * @return el angulo formado entre los puntos p1 y p2
     */

    private double ang(Point p1, Point p2) {
        double ang = 0.0;
        if (p1.getX() == p2.getX()) {
            if (p1.getY() >= p2.getY())
                ang = 270.0;
            else
                ang = 90.0;
        }
        if (p1.getY() == p2.getY()) {
            if (p1.getX() > p2.getX())
                ang = 180.0;
            else
                ang = 0.0;
        }
        if ((p1.getX() != p2.getX()) && (p1.getY() != p2.getY()))
                ang = 180.0
                        * StrictMath.atan((p2.getY() - p1.getY())
                                / (p2.getX() - p1.getX())) / StrictMath.PI;
        ang = StrictMath.abs(ang);
        //CALCULO DEL ANGULO, 4 CASOS
        if ((p1.getX() < p2.getX()) && (p1.getY() < p2.getY())) {
        }//angulo calculado
        if ((p1.getX() < p2.getX()) && (p1.getY() > p2.getY()))
                ang = 360 - ang;
        if ((p1.getX() > p2.getX()) && (p1.getY() < p2.getY()))
                ang = 180 - ang;
        if ((p1.getX() > p2.getX()) && (p1.getY() > p2.getY())) ang += 180;
        return ang;
    }
}