package Experiment.Exp5_6;

import java.util.*;

public class ShellConsole {
    private final Map<String, Command> commands = new HashMap<>();
    public static final Map<String, ElecticalAppliance> devices = new HashMap<>();
    public static SeriesCircuit mainCircuit = null;

    // 注册命令
    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    // 启动控制台
    public void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Welcome to Shell Console! Type 'end' to run.");

        while (true) {
//            System.out.print("> "); // 提示符
            String input = scanner.nextLine().trim();
            // 退出命令
            if ("end".equalsIgnoreCase(input)) {
                ShellConsole.mainCircuit.run(220, 0, -1);
                display();

//                System.out.println("Goodbye!");
                break;
            }

            // 解析命令
            String commandName = input.substring(0, 1);
            String arg = input.substring(1);
            String[] args = new String[1];
            args[0] = arg;

            // 执行命令
            Command command = commands.get(commandName);
            if (command != null) {
                command.execute(args);
//                } catch (Exception e) {
//                    System.err.println("Error: " + e.getMessage());
//                }
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
    public static void main(String[] args) throws Exception {
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
            String devicename, s1 = s.substring(0, s.length());
            int num;
            try {
                devicename = s.substring(0, s.indexOf(':'));
                num = Integer.parseInt(s.substring(1, s.indexOf(':')));
            } catch (StringIndexOutOfBoundsException e) {
                devicename = s;
                num = Integer.parseInt(s.substring(1));
            }
            // 注册串并联电路,或者设置开关状态。
            switch (s.charAt(0)) {
                    /*
                    注册串并联电路。
                     */
                case 'T':
//                    System.out.println("init");
                    console.devices.put(devicename, new SeriesCircuit(num, devicename));
                    serieslink(console, (SeriesCircuit) console.devices.get(devicename), s.substring(s.indexOf(':') + 1));
                    break;
                case 'M':
                    console.devices.put(devicename, new ParallelCircuit(num, devicename));
                    parrallelink(console, (ParallelCircuit) console.devices.get(devicename), s.substring(s.indexOf(':') + 1));
                    break;
                    /*
                    设置开关状态。
                     */
                case 'K':
                    ((Switch) console.devices.get(s1)).switchState();
                    break;
                case 'F':
                    ((SteppedRegulator) console.devices.get(s1)).setStep(s.charAt(s.length() - 1));
                    console.devices.put(devicename, new SteppedRegulator(num, s1));
                    break;
                case 'L':
                    ((ContinuousRegulator) console.devices.get(s1)).setRate(Double.parseDouble(s.substring(s.indexOf(':') + 1)));
                    console.devices.put(devicename, new ContinuousRegulator(num, devicename));
                    break;
                default:
                    System.out.println("Wrong Input!");
            }
        });

        console.registerCommand("help", args1 -> {
            System.out.println("Available commands: [ , # , help ");
        });

        console.registerCommand("show", args1 -> {
            for (ElecticalAppliance device : console.devices.values()) {
                device.display();
            }
        });

        console.start();
    }

    /**
     * Links electrical appliances in a parallel circuit.
     *
     * @param console The ShellConsole instance managing the devices.
     * @param circuit The ElecticalAppliance representing the parallel circuit.
     * @param args    The string containing the devices to be linked in the circuit.
     */
    public static void parrallelink(ShellConsole console, ParallelCircuit circuit, String args) {
        ArrayList<String> segments = parseString(args);

        for (String s : segments) {
            // 解析当前操作对象

            String[] parts = s.split("-");
            String name = parts[0];
            int num = Integer.parseInt(name.substring(1));
            circuit.addAppliance(console.devices.get(name));
//
//            if (console.devices.get(name) == null) {
//                //   若设备不存在,注册
//                switch (s.charAt(0)) {
//                    /*
//                    串联电路注册::: 无需注册，因为能嵌套肯定已经注册了。
//                     */
//
//                    /*
//                    电器注册
//                     */
//                    case 'B':
//                        console.devices.put(name, new IncandescentLamp(num, name));
//                        circuit.addAppliance(console.devices.get(name));
//                        break;
//                    case 'R':
//                        console.devices.put(name, new FluorescentLamp(num, name));
//                        circuit.addAppliance(console.devices.get(name));
//                        break;
//                    case 'D':
//                        console.devices.put(name, new CeilingFan(num, name));
//                        circuit.addAppliance(console.devices.get(name));
//                        break;
//                    default:
//                        System.out.println("Wrong Input!");
//                        return;
//                }
//            } else {
//            circuit.addAppliance(console.devices.get(name));
//            }
        }
    }

    public static void serieslink(ShellConsole console, SeriesCircuit circuit, String args) {
        ArrayList<String> segments = parseString(args);
        ElecticalAppliance parent = null;
        String parentname = null;
        for (String s : segments) {
            // 解析当前操作对象
            int num;
            String[] parts = s.split("-");
            String name = parts[0];
//            System.out.println(name);
            // 避免无法取到数字编号.
            try {
                num = Integer.parseInt(name.substring(1));
            } catch (NumberFormatException e) {
                num = 0;
            }


            if (console.devices.get(name) == null) {
                //   若设备不存在， 则注册
                switch (s.charAt(0)) {
                    /*
                    TODO： 控制器注册
                     */
                    case 'K':
                        console.devices.put(name, new Switch(num, name));
                        break;
                    case 'F':
                        console.devices.put(name, new SteppedRegulator(num, name));
                        break;
                    case 'L':
                        console.devices.put(name, new ContinuousRegulator(num, name));
                        break;
                    case 'H':
                        console.devices.put(name, new Mutexswitch(num, name));
                        break;
                    case 'P':
                        console.devices.put(name, new Diode(num, name));
                        break;

                    /*
                    TODO: 电器注册
                     */
                    case 'B':
                        console.devices.put(name, new IncandescentLamp(num, name));
                        break;
                    case 'R':
                        console.devices.put(name, new FluorescentLamp(num, name));
                        break;
                    case 'D':
                        console.devices.put(name, new CeilingFan(num, name));
                        break;
                    case 'S':
                        console.devices.put(name, new Curtain(num, name));
                        break;

                    /*
                    嵌套电路注册 ::::: 无需注册，因为能嵌套肯定已经注册了。
                     */
                    case 'I':
                        if (!name.equals("IN"))
                            throw new IllegalArgumentException("Wrong Input!");
                        continue;
                    case 'O':
                        if (!name.equals("OUT"))
                            throw new IllegalArgumentException("Wrong Input!");
                        continue;
                    case 'G':
                        if (name.equals("GND")) {
                            console.devices.put(name, new GND(name));
                        } else {
                            throw new IllegalArgumentException("Wrong Input!");
                        }
                        break;
                    case 'V':
                        if (name.equals("VCC")) {
                            console.devices.put(name, new VCC(name));
                            ShellConsole.mainCircuit = circuit;
                        } else {
                            throw new IllegalArgumentException("Wrong Input!");
                        }
                        break;
                    default:
                        System.out.println("设备注册失败！");
                        return;
                }
            }

            ElecticalAppliance child = console.devices.get(name);

            // 成对 连线解析
            if (parent == child) {
                if (parent instanceof Mutexswitch) {
                    // 互斥开关的连接方式。
                    if ((parentname.substring(parentname.indexOf('-') + 1).equals("1") && s.substring(s.indexOf('-') + 1).equals("2")) || (parentname.substring(parentname.indexOf('-') + 1).equals("2") && s.substring(s.indexOf('-') + 1).equals("1")))
                        circuit.addAppliance(((Mutexswitch) parent).init(2));
                    else if ((parentname.substring(parentname.indexOf('-') + 1).equals("1") && s.substring(s.indexOf('-') + 1).equals("3")) || (parentname.substring(parentname.indexOf('-') + 1).equals("3") && s.substring(s.indexOf('-') + 1).equals("1")))
                        circuit.addAppliance(((Mutexswitch) parent).init(3));
                }else if(parent instanceof Diode){
                    // 二极管的连接方式。
                    circuit.addAppliance(((Diode)child));
                    if(parentname.substring(parentname.indexOf('-') + 1).equals("1") && s.substring(s.indexOf('-') + 1).equals("2")){
                        ((Diode) child).setStatus(true);
                    }else{
                        ((Diode) child).setStatus(false);
                    }
                }
                else {
                    circuit.addAppliance(child);
                }
                parent = null;
                parentname = null;
            } else {
                parent = console.devices.get(name);
                parentname = s;
            }

        }

    }

    // 解析成单独的设备。
    public static ArrayList<String> parseString(String input) {
        input = input.replaceAll("\\[", "").replaceAll("]", "");
//        System.out.println(input);
        String[] parts = input.split(" ");
        return new ArrayList<>(Arrays.asList(parts));
    }

    public void display() {
        String[] str = {"K", "F", "L", "B", "R", "D", "A", "H", "S"};
        for (String s : str) {
            for (int i = 1; i < devices.size() + 1; i++) {
                try {
//                    System.out.println(s+i);
                    devices.get(s + i).display();
                } catch (NullPointerException e) {
                    continue;
                }
            }
        }
    }
}
