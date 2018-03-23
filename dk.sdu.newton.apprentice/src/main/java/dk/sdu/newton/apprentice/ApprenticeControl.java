package dk.sdu.newton.apprentice;

public class ApprenticeControl {
    private Apprentice source;
    private float movement[] = new float[2];

    public ApprenticeControl(Apprentice source){
        this.source = source;
    }
    private boolean getRandomBoolean() {
        return Math.random() < 0.5;
    }

    public float getdx(){
        if(getRandomBoolean() == true) {
            return 10;
        }else{
            return 0;
    }}
        public float getdy(){
            if(getRandomBoolean() == true) {
                return 10;
            }else{
                return 0;
            }

}}
