package Experiment.Exp5;

public class ControlSystem {
    public static void main(String[] args) {

    }

    void connect(ElecticalAppliance appliance1, ElecticalAppliance appliance2) {
        appliance2.setVoltage(1, appliance1.getVoltage(2));
    }

    void parseLines_version01(String[] lines){
        for(String line : lines){
//            if ()
            parseLine_version01(line);
        }
    }

    void parseLine_version01(String line){

    }

//
//    void inputParse(String[] lines) {
//        if (lines.length == 0) return;
//
//        double v;
//
//        for (String line : lines) {
//            List<String> command = lineParse(line);
//            switch (command.get(0)) {
//                case "[":
//                    command.remove(0);
//                    for (String cmd : command) {
//                        if (cmd.compareTo("VCC")==0)
//                            v = 220;
////                        else if(cmd.compareTo("F1")==0)
//                    }
//                    break;
//            }
//        }
//    }
//
//    List<String> lineParse(String line) {
//        String regex = "\\b[A-Z0-9]+(-[A-Z0-9]+)?\\b";
//        Pattern pattern = Pattern.compile(regex);
//
//        if (line.charAt(0) == '[') {
//            List<String> matches = new ArrayList<>();
//            matches.add("[");
//            Matcher matcher = pattern.matcher(line);
//
//            while (matcher.find()) {
//                matches.add(matcher.group());
//            }
//
//            return matches;
//        } else if (line.charAt(0) == '#') {
//            List<String> matches = new ArrayList<>();
//
//            for (int i = 0; i < line.length(); i++) {
//                matches.add(line.charAt(i) + "");
//            }
//
//            return matches;
//        } else if (line.charAt(0) == 'e') {
//            List<String> matches = new ArrayList<>();
//            if (line.compareTo("end") == 0) {
//                matches.add("end");
//                return matches;
//            }
//        }
//        return null;
//    }


}
