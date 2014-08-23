/*
 * Created on 28-feb-2005
 *
 */
package weapons;

import java.awt.*;

import util.*;

/**
 * @author David
 * 
 */
public class Explossion {

    public static final int EXPLOSION_FACTOR = 50;

    public static final int POLYGON = 1;

    public static final int FRACTAL_POLYGON = 2;

    public static final int FRACTAL_POLYGON2 = 3;
    
    public static final int FRACTAL_POLYGON3 = 4;

    public static final int FRACTAL_POLYGON4 = 5;
    
    public static final int FRACTAL_POLYGON5 = 6;
    
    public static final int FRACTAL_POLYGON6 = 7;
    
    public static final int FRACTAL_POLYGON7 = 8;
    
    public static final int IRREGULAR_POLYGON = 9;

    private Shape shape;

    private int type;

    private double ratio;

    private int numberOfPoints;
    
    private Point position;


    /**
     * Constructor
     * 
     * @param type El tipo de explosinhn que se generarnh
     * @param numberOfPoints El nnhmero de vnhrtices de la figura que generarnh la
     *            explosinhn
     */

    public Explossion(Point position, int type, int numberOfPoints,
            double ratio) {

        init(position, type, numberOfPoints, ratio);
    }


    /**
     * Crea la figura que se pintarnh en pantalla para la explosinhn dependiendo
     * de su tipo
     *  
     */

    public void init(Point position, int type, int numberOfPoints,
            double ratio) {

        this.type = type;
        this.numberOfPoints = numberOfPoints;
        this.ratio = ratio;
        this.position = position;

        switch (type) {
        case POLYGON:
            //ratio = makeRatio();
            Point[] points = makePoints();
            int[] x = new int[this.numberOfPoints];
            int[] y = new int[this.numberOfPoints];
            for (int i = 0; i < this.numberOfPoints; i++) {
                x[i] = (int) points[i].getX();
                y[i] = (int) points[i].getY();
            }
            shape = new Polygon(x, y, numberOfPoints);
            break;
        case FRACTAL_POLYGON:
        case FRACTAL_POLYGON2:	
        case FRACTAL_POLYGON3:	
        case FRACTAL_POLYGON4:	
        case FRACTAL_POLYGON5:
        case FRACTAL_POLYGON6:
        case FRACTAL_POLYGON7:
            ratio = makeRatio();
            Point[] points2 = makePoints();
            int[] x2 = new int[this.numberOfPoints];
            int[] y2 = new int[this.numberOfPoints];
            for (int i = 0; i < this.numberOfPoints; i++) {
                x2[i] = (int) points2[i].getX();
                y2[i] = (int) points2[i].getY();
            }
            shape = new Polygon(x2, y2, numberOfPoints);
            switch (type){
            case FRACTAL_POLYGON:
              Fractal fractal = new Fractal((Polygon) shape, 2 * numberOfPoints,Explossion.FRACTAL_POLYGON);
              shape = fractal.getPolygon();
              break;
            case FRACTAL_POLYGON2:  
              Fractal fractal2 = new Fractal((Polygon) shape, 5 * numberOfPoints,Explossion.FRACTAL_POLYGON2);
              shape = fractal2.getPolygon();
              break;
            case FRACTAL_POLYGON3:
              Fractal fractal3 = new Fractal((Polygon) shape, 13 * numberOfPoints,Explossion.FRACTAL_POLYGON3);
              shape = fractal3.getPolygon();   	
              break;
            case FRACTAL_POLYGON4:
                Fractal fractal4 = new Fractal((Polygon) shape, 2 * numberOfPoints,Explossion.FRACTAL_POLYGON);
                shape = fractal4.getPolygon();  
                Fractal fractal41 = new Fractal((Polygon) shape, 4 * numberOfPoints,Explossion.FRACTAL_POLYGON);
                shape = fractal41.getPolygon(); 
                break;
            case FRACTAL_POLYGON5:
                Fractal fractal5 = new Fractal((Polygon) shape, 2 * numberOfPoints,Explossion.FRACTAL_POLYGON);
                shape = fractal5.getPolygon();
                Fractal fractal51 = new Fractal((Polygon) shape, 26 * numberOfPoints,Explossion.FRACTAL_POLYGON3);
                shape = fractal51.getPolygon(); 
                break;
            case FRACTAL_POLYGON6:
                Fractal fractal6 = new Fractal((Polygon) shape, 5 * numberOfPoints,Explossion.FRACTAL_POLYGON2);
                shape = fractal6.getPolygon(); 
                Fractal fractal61 = new Fractal((Polygon) shape, 65 * numberOfPoints,Explossion.FRACTAL_POLYGON3);
                shape = fractal61.getPolygon();  
                break;
            case FRACTAL_POLYGON7:
                Fractal fractal7 = new Fractal((Polygon) shape, 2 * numberOfPoints,Explossion.FRACTAL_POLYGON);
                shape = fractal7.getPolygon();   	
                Fractal fractal71 = new Fractal((Polygon) shape, 10 * numberOfPoints,Explossion.FRACTAL_POLYGON2);
                shape = fractal71.getPolygon();
                Fractal fractal72 = new Fractal((Polygon) shape, 130 * numberOfPoints,Explossion.FRACTAL_POLYGON3);
                shape = fractal72.getPolygon();
                break;    
            default:
                break;	
            } 
            break;
        case IRREGULAR_POLYGON:
        	//POLIGONO CONSTRUIDO A CAPON CON 13 LADOS
        	 Point[] points3 = makeIrregularPoints();
             int[] x3 = new int[this.numberOfPoints];
             int[] y3 = new int[this.numberOfPoints];
             for (int i = 0; i < this.numberOfPoints; i++) {
                 x3[i] = (int) points3[i].getX();
                 y3[i] = (int) points3[i].getY();
             }
             shape = new Polygon(x3, y3, numberOfPoints);
            break;
        default:
            break;
        }

    }


    /**
     * Accesor
     * 
     * @return La figura que generarnh la explosinhn
     */

    public Shape getShape() {
        return this.shape;
    }


    /**
     * Mutador
     * 
     * @param shape La figura que generarnh la explosinhn
     */

    public void setShape(Shape shape) {
        this.shape = shape;
    }


    /**
     * Accesor
     * 
     * @param ratio El radio de la explosinhn
     */

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }


    /**
     * Accesor
     * 
     * @return el radio para construir la figura
     */

    public double getRatio() {
        return ratio;
    }


    /**
     * Accesor
     * 
     * @return el tipo de la figura
     */

    public void setType(int type) {
        this.type = type;
    }


    /**
     * Accesor
     * 
     * @return El tipo de la explosinhn
     */

    public int getType() {
        return type;
    }


    /**
     * Mutador
     * 
     * @param number el nnhmero de puntos de la figura
     */

    public void setNumberOfPoints(int number) {
        this.numberOfPoints = number;
    }


    /**
     * Accesor
     * 
     * @return El nnhmero de puntos de la figura
     */

    public int getNumberOfPoints() {
        return numberOfPoints;
    }


    /**
     * Construye el radio de la figura
     * 
     * @return El radio de la figura en funcinhn de la potencia del proyectil
     */

    private double makeRatio() {
        double exp = (EXPLOSION_FACTOR);
        return exp;
    }


    /**
     * Construye el polnhgono de numero de lados = numberOfPoints con ayuda del
     * lnhpiz.
     * 
     * @return El array de vertices de la figura
     */

    private Point[] makePoints() {
        /*
         * Utilizamos el mnhtodo para pintar poligonos Primero calculamos el
         * centro del polnhgono que originarnh la explosinhn, despunhs el primer
         * vnhrtice del polnhgono, y finalmente lo construiremos con ayuda del
         * Pencil
         */
        Point centro = new Point();
        centro.setLocation(position);
        Point initial = new Point();
        int x = (int) (centro.getX() + ratio);
        int y = (int) centro.getY();
        initial.setLocation(x, y);
        double ang, longside; //longitud del lado del polnhgono
        ang = 180 / (double) numberOfPoints;
        longside = 2 * ratio * StrictMath.sin((StrictMath.PI / 180) * ang);
        Point p = new Point();
        p.setLocation(initial);
        Pencil pencil = new Pencil(p, 90 + ang);
        Point[] points = new Point[this.numberOfPoints];
        for (int i = 0; i < this.numberOfPoints; i++)
            points[i] = new Point();
        for (int i = 0; i < this.numberOfPoints; i++) {
            points[i].setLocation(pencil.getPosition());
            pencil.nextStep(longside);
            pencil.turn(ang * 2);
        }
        return points;
    }
    
    /*
     * Funcion para construir un poligono A CAPON(Irregular)
     */
    
    private Point[] makeIrregularPoints() {
        Point centro = new Point();
        centro.setLocation(position);
        Point initial = new Point();
        int x = (int) (centro.getX() + ratio);
        int y = (int) centro.getY();
        initial.setLocation(x, y);
        double longside; //longitud del lado del polnhgono
        longside = this.ratio;
        Point p = new Point();
        p.setLocation(initial);
        Pencil pencil = new Pencil(p,100.0);
        Point[] points = new Point[13];
        for (int i = 0; i < 13; i++)
            points[i] = new Point();
        points[0].setLocation(initial);
        pencil.nextStep(longside);
        pencil.turnTo(235.0);
        points[1].setLocation(pencil.getPosition());
        pencil.nextStep(longside / 1.5);
        pencil.turnTo(110.0);
        points[2].setLocation(pencil.getPosition());
        pencil.nextStep(longside);
        pencil.turnTo(0.0);
        points[3].setLocation(pencil.getPosition());
        pencil.nextStep(longside);
        pencil.turnTo(260.0);
        points[4].setLocation(pencil.getPosition());
        pencil.nextStep(longside);
        pencil.turnTo(110.0);
        points[5].setLocation(pencil.getPosition());
        pencil.nextStep(longside);
        pencil.turnTo(270.0);
        points[6].setLocation(pencil.getPosition());
        pencil.nextStep(longside*1.3);
        pencil.turnTo(300.0);
        points[7].setLocation(pencil.getPosition());
        pencil.nextStep(longside*1.5);
        pencil.turnTo(200.0);
        points[8].setLocation(pencil.getPosition());
        pencil.nextStep(longside/2.5);
        pencil.turnTo(330.0);
        points[9].setLocation(pencil.getPosition());
        pencil.nextStep(longside*2.3);
        pencil.turnTo(90.0);
        points[10].setLocation(pencil.getPosition());
        pencil.nextStep(longside);
        pencil.turnTo(335.0);
        points[11].setLocation(pencil.getPosition());
        pencil.nextStep(longside/1.7);
        points[12].setLocation(pencil.getPosition());
        return points;
    }
}