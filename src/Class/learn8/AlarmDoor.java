package Class.learn8;

public class AlarmDoor extends Door {
    private Alarmer alarmer;

    public AlarmDoor(Alarmer alarmer) {
        this.alarmer = alarmer;
    }

    public void ring(){
        alarmer.ring();
    }

//    public void shutDown(){
//        alarmer
//    }


}
