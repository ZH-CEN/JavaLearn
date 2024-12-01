package Class.HomeWork.HM2;

public class Light {
    private String[] colorTemperatures = {"white", "warm white", "warm"};
    private int currentColorIndex;  // 当前色温的索引，0代表白光，1代表暖白光，2代表暖光
    private boolean isOn;  // 标识灯是否处于打开状态

    public Light() {
        this.currentColorIndex = 0;  // 初始状态为白光
        this.isOn = false;  // 灯初始状态为关闭
    }

    // 获取当前灯光状态
    public boolean isOn() {
        return isOn;
    }

    // 切换灯光开关
    public void toggle() {
        isOn = !isOn;
        if (isOn) {
            System.out.println("Light turned on. Current color: " + colorTemperatures[currentColorIndex]);
        } else {
            System.out.println("Light turned off.");
        }
    }

    // 循环切换色温
    public void changeColorTemperature() {
        if (isOn) {
            currentColorIndex = (currentColorIndex + 1) % colorTemperatures.length;
            System.out.println("Light color changed to: " + colorTemperatures[currentColorIndex]);
        } else {
            System.out.println("Light is off. Cannot change color.");
        }
    }
}
