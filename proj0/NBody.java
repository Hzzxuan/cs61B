import java.io.File;
import java.util.Scanner;

public class NBody {
    public static double readRadius(String s){
        In in = new In(s);
        int n = in.readInt();
        double R =in.readDouble();
        return R;
    }

    public  static  Planet[] readPlanets(String s){
        In in = new In(s);
        int n = in.readInt();
        double R =in.readDouble();
        Planet[] PlanetArray= new Planet[5];
        for(int i=0;i<5;i=i+1){
            double xxp = in.readDouble();
            double yyp = in.readDouble();
            double xxv = in.readDouble();
            double yyv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            PlanetArray[i] = new Planet(xxp,yyp,xxv,yyv,m,img);
        }
        return  PlanetArray;
    }

    public static  void main(String[] args){
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename = args[2];
        double Radius=NBody.readRadius(filename);
        Planet[] PlanetArray=NBody.readPlanets(filename);
        //StdDraw.setPenRadius(Radius);
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(-Radius, Radius);
        StdDraw.setYscale(-Radius, Radius);


        double t=0;
        while(t<=T){
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for(int i=0;i<PlanetArray.length;i=i+1){
                xForces[i]=PlanetArray[i].calcNetForceExertedByX(PlanetArray);
                yForces[i]=PlanetArray[i].calcNetForceExertedByY(PlanetArray);
            }
            for(int i=0;i<PlanetArray.length;i=i+1){
                PlanetArray[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0, 0,"images/starfield.jpg");
            for(Planet x:PlanetArray){
                x.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t=t+dt;
        }
        StdOut.printf("%d\n", PlanetArray.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < PlanetArray.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    PlanetArray[i].xxPos, PlanetArray[i].yyPos, PlanetArray[i].xxVel,
                    PlanetArray[i].yyVel, PlanetArray[i].mass, PlanetArray[i].imgFileName);
        }
    }
}
