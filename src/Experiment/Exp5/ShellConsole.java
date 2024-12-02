package Experiment.Exp5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShellConsole {
    private Map<String, Command> commands = new HashMap<>();
    private Map<String, ElecticalAppliance> devices = new HashMap<>();

    // 注册命令
    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    // 启动控制台
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Shell Console! Type 'end' to quit.");

        while (true) {
            System.out.print("> "); // 提示符
            String input = scanner.nextLine().trim();

            // 退出命令
            if ("end".equalsIgnoreCase(input)) {
                this.devices.get("VCC").run(220);
                System.out.println("Goodbye!");
                break;
            }

            // 解析命令
            String[] parts = input.split("\\s+");
            String commandName = parts[0];
            String[] args = new String[parts.length - 1];
            System.arraycopy(parts, 1, args, 0, args.length);

            // 执行命令
            Command command = commands.get(commandName);
            if (command != null) {
                try {
                    command.execute(args);
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Command not found: " + commandName);
            }
        }

        scanner.close();
    }

    // 命令接口
    public interface Command {
        void execute(String[] args) throws Exception;
    }

    // 示例主程序
    public static void main(String[] args) {
        ShellConsole console = new ShellConsole();

        // 注册一些示例命令
        console.registerCommand("[", args1 -> {
//            int lineflag = 0;
            ElecticalAppliance parent = null;
            for (String s : args1) {
                if (s.equals("]")) {
                    System.out.println("Done!");
                    return;
                } else {
                    // 解析当前操作对象
                    String[] parts = s.split("-");
                    String name = parts[0];

                    if (console.devices.get(parts[0]) == null) {
                        //   若设备不存在
                        switch (s.charAt(0)) {
                            case 'V':
                                console.devices.put(name, new VCC(s));
//                                System.out.println(name);
                                break;
                            case 'K':
                                console.devices.put(name, new Switch(Character.getNumericValue(name.charAt(1)), s));
                                break;
                            case 'F':
                                console.devices.put(name, new SteppedRegulator(Character.getNumericValue(name.charAt(1)), s));
                                break;
                            case 'L':
                                console.devices.put(name, new ContinuousRegulator(Character.getNumericValue(name.charAt(1)), s));
                                break;
                            case 'B':
                                console.devices.put(name, new IncandescentLamp(Character.getNumericValue(name.charAt(1)), s));
                                break;
                            case 'R':
                                console.devices.put(name, new FluorescentLamp(Character.getNumericValue(name.charAt(1)), s));
                                break;
                            case 'D':
                                console.devices.put(name, new CeilingFan(Character.getNumericValue(name.charAt(1)), s));
                                break;
                            case 'G':
                                console.devices.put(name, new GND(s));
                                break;
                            default:
                                System.out.println("Wrong Input!");
                                return;
                        }
                    }
                    if (parent != null) {
                        parent.children.add(console.devices.get(parts[0]));
                        parent = null;
                    } else
                        parent = console.devices.get(name);
                }
            }
            System.out.println("Wrong Input!");
        });

        console.registerCommand("#", args1 -> {
            String s = args1[0];
            String devicename = s.substring(0, 2);
            ElecticalAppliance device = console.devices.get(devicename);
            switch (s.charAt(0)) {
                case 'K':
                    ((Switch) device).switchState();
                    break;
                case 'F':
                    ((SteppedRegulator) device).setStep('+');
                    break;
                case 'L':
                    String[] splits = s.split(":");
                    double rate = Double.parseDouble(splits[1]);
                    ((ContinuousRegulator) device).setRate(rate);
            }

        });

        console.registerCommand("help", args1 -> {
            System.out.println("Available commands: [ , # , help ");
        });

        console.registerCommand("show", args1 -> {
            for ( ElecticalAppliance device : console.devices.values()) {
                device.display();
            }
        });


        console.start();
    }
}
