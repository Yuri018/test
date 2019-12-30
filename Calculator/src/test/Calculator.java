package test;

public class Calculator {
    public static void main(String[] args) {
        String consoleText = "";
        while (true){
            consoleText = Util.getConsoleString();
            if (consoleText.equals("exit")) return;
            Util.validate(consoleText);
            Integer[] ints = Util.intsFromString(consoleText);
            Util.checkBounds(ints[0],ints[1]);
            int result;
            switch (consoleText.split(" ")[1]){
                case "+": result = ints[0]+ints[1]; break;
                case "-": result = ints[0]-ints[1]; break;
                case "*": result = ints[0]*ints[1]; break;
                case "/": result = ints[0]/ints[1]; break;
                default: result=0;
            }
            Util.printResult(result,consoleText);
        }
    }
}
