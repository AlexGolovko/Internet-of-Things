public class AM2320 {
    static {
        System.loadLibrary("am2320_c");
    }

    private native double getTemperature();

    private native double getHumidity();

    public static void main(String[] args) {
        AM2320 am2320 = new AM2320();
        try {
            System.out.println("Get Temperature:");
            am2320.getTemperature();
            System.out.println("Get Humidity:");
            am2320.getHumidity();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}