package Classes;

// this is basic implementation of GeoLocation
public class GeoLoc implements api.GeoLocation {

    double x;
    double y;
    double z;

    public GeoLoc(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(api.GeoLocation g) {
        double x=g.x(),y=g.y(),z=g.z();
        return Math.sqrt(Math.pow(this.x-x,2) + Math.pow(this.y-y,2) + Math.pow(this.z-z,2));
    }

}
