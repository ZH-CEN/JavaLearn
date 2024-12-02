package Experiment.Exp5;

import java.util.ArrayList;
import java.util.List;

public class CircuitNode {
    String deviceName; // 设备名，仅当 type=DEVICE 时有效
    List<ElecticalAppliance> children; // 子节点，仅当 type=SERIES 或 PARALLEL 时有效

    CircuitNode(){

    }
    // 构造器
    CircuitNode(String deviceName) {
//        this.type = type;
        this.deviceName = deviceName;
        this.children = new ArrayList<>();
    }

    void addChild(ElecticalAppliance child) {
        this.children.add(child);
    }
}
