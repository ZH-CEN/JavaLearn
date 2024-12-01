package Class.HomeWork.HM2;

public class Main {
    public static void main(String[] args) {
        Fan fan = new Fan();
        Light light = new Light();
        ControlPanel controlPanel = new ControlPanel(fan, light);

        // 打开灯光并改变色温
        controlPanel.toggleLight();  // 打开灯
        controlPanel.changeLightColor();  // 切换到暖白光
        controlPanel.changeLightColor();  // 切换到暖光

        // 调整风扇速度
        controlPanel.adjustFanSpeed();  // 低速
        controlPanel.adjustFanSpeed();  // 中速
        controlPanel.adjustFanSpeed();  // 高速

        // 关闭灯光
        controlPanel.toggleLight();  // 关闭灯
    }
}

