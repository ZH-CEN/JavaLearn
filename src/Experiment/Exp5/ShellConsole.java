package Experiment.Exp5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ShellConsole {
    private Map<String, Command> commands = new HashMap<>();

    // 注册命令
    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    // 启动控制台
    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Shell Console! Type 'exit' to quit.");

        while (true) {
            System.out.print("> "); // 提示符
            String input = scanner.nextLine().trim();

            // 退出命令
            if ("exit".equalsIgnoreCase(input)) {
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
            int flag = 0;

            for (String s : args1) {
                if (flag == 0 && s.equals("[")) {
                    flag = 1;
                }
                else if (flag == 1 && s.equals("]")) {
                    flag = 0;
                    System.out.println("Done!");
                    return;
                }
                else if (flag == 1){
//                   // TODO:
                }
            }
            System.out.println("Wrong Input!");
            return;

        });

        console.registerCommand("add", args1 -> {
            if (args.length != 2) {
                throw new IllegalArgumentException("Usage: add <num1> <num2>");
            }
            int num1 = Integer.parseInt(args1[0]);
            int num2 = Integer.parseInt(args1[1]);
            System.out.println("Result: " + (num1 + num2));
        });

        console.registerCommand("help", args1 -> {
            System.out.println("Available commands: echo, add, help, exit");
        });

        console.start();
    }
}
