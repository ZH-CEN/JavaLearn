package Class.HomeWork.HM2;

public class ControlPanel {
    private Fan fan;  // 控制风扇
    private Light light;  // 控制灯

    public ControlPanel(Fan fan, Light light) {
        this.fan = fan;
        this.light = light;
    }

    // 切换灯的开关
    public void toggleLight() {
        light.toggle();
    }

    // 改变灯的色温
    public void changeLightColor() {
        light.changeColorTemperature();
    }

    // 控制风扇档位
    public void adjustFanSpeed() {
        if (light.isOn()) {  // 只有在灯开着的情况下，才允许调整风扇
            int currentSpeed = fan.getSpeed();
            int newSpeed = (currentSpeed + 1) % 4;  // 0-停止, 1-低速, 2-中速, 3-高速
            fan.setSpeed(newSpeed);
        } else {
            System.out.println("Light is off. Fan cannot be adjusted.");
        }
    }
}