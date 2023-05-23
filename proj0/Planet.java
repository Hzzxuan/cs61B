public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static  final double G=6.67e-11;

    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public Planet(Planet b){
        this.xxPos=b.xxPos;
        this.yyPos=b.yyPos;
        this.xxVel=b.xxVel;
        this.yyVel=b.yyVel;
        this.mass = b.mass;
        this.imgFileName=b.imgFileName;
    }

    public double calcDistance(Planet b){
        double r;
        r=(this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+(this.yyPos-b.yyPos)*(this.yyPos-b.yyPos);
        return Math.sqrt(r);
    }

    public double calcForceExertedBy(Planet b){
        double F;
        double r=this.calcDistance(b);
        F=(Planet.G*b.mass*this.mass)/(r*r);
        return F;
    }

    public double calcForceExertedByX(Planet b){
        double F=this.calcForceExertedBy(b);
        double r=this.calcDistance(b);
        double dx=b.xxPos-this.xxPos;
        double Fx;
        Fx=F*dx/r;
        return Fx;
    }

    public double calcForceExertedByY(Planet b){
        double F=this.calcForceExertedBy(b);
        double r=this.calcDistance(b);
        double dy=b.yyPos-this.yyPos;
        double Fx;
        Fx=F*dy/r;
        return Fx;
    }
    public double calcNetForceExertedByX(Planet[] PlanetArray){
        double FxSum=0;
        for(Planet planet :PlanetArray){
            if (this.equals(planet)){
                continue;
            }
            double Fx=this.calcForceExertedByX(planet);
            FxSum += Fx;
        }
        return FxSum;
    }
    public double calcNetForceExertedByY(Planet[] PlanetArray){
        double FySum=0;
        for(Planet planet :PlanetArray){
            if (this.equals(planet)){
                continue;
            }
            double Fy=this.calcForceExertedByY(planet);
            FySum += Fy;
        }
        return FySum;
    }
    public void update(double dt,double fX,double fY){
        double aX=fX/this.mass;
        double aY=fY/this.mass;
        this.xxVel=this.xxVel+aX*dt;
        this.yyVel=this.yyVel+aY*dt;
        this.xxPos=this.xxPos+this.xxVel*dt;
        this.yyPos=this.yyPos+this.yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
