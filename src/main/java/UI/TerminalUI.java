package UI;

import Elevator.Elevator;
import Elevator.Direction;
import Elevator.ElevatorStatus;
import Elevator.IElevator;
import Elevator.MinOverheadElevator;
import ElevatorsSystem.IElevatorsSystem;
import ElevatorsSystem.LowestCostElevatorsSystem;

import java.util.ArrayList;
import java.util.Scanner;

public class TerminalUI {
    public static final int ELEVATORS_NUMBER = 16;
    public static final int START_FLOOR = 0;
    private static IElevatorsSystem elevatorSystem = null;

    public static void init() {
        ArrayList<IElevator> elevators = new ArrayList<>();
        for (int i = 0; i < ELEVATORS_NUMBER; i++) {
            elevators.add(new MinOverheadElevator(START_FLOOR));
        }
        elevatorSystem = new LowestCostElevatorsSystem(elevators);
    }

    public static void main(String[] args) {
        init();
        System.out.println("*** Welcome to ElevatorsSystem ***\n");
        printInstruction();
        printInitialStatus();
        feedbackLoop();
    }

    private static void printInstruction() {
        System.out.println("Use the system by typing commands in stdin:");
        System.out.println("Print the status:");
        System.out.println("  STATUS");
        System.out.println("Call an elevator, returns the elevator number of the assigned elevator:");
        System.out.println("  CALL <floor number> <UP|DOWN>");
        System.out.println("Choose a destination in an elevator:");
        System.out.println("  DEST <elevator number> <floor number>");
        System.out.println("Run the simulation:");
        System.out.println("  SIM <number of steps>");
        System.out.println("to exit type QUIT");
        System.out.println("");
    }

    private static void printInitialStatus() {
        System.out.println("System info: " + ELEVATORS_NUMBER + " elevators," +
                " starting at floor nr: " + START_FLOOR);

    }

    private static void feedbackLoop() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String line = scan.nextLine();
                String[] splitLine = line.split("\s+");
                if (splitLine.length > 0) {
                    switch (splitLine[0]) {
                        case "STATUS":
                            status();
                            break;
                        case "CALL":
                            call(splitLine);
                            break;
                        case "DEST":
                            chooseDestination(splitLine);
                            break;
                        case "SIM":
                            simulate(splitLine);
                            break;
                        case "QUIT":
                            return;
                        default:
                            System.out.println("Unrecognized command");
                            break;
                    }
                }
            } catch (InvalidCommandArguments e) {
                System.out.println("Invalid arguments: " + e.getMessage());
            }
        }
    }


    private static void checkArgumentsNumber(String[] splitLine, int expected) throws InvalidCommandArguments {
        if (splitLine.length < expected + 1) {
            throw new InvalidCommandArguments("too few numbers of arguments, expected" + expected);
        }
    }

    private static void status() {
        ArrayList<IElevator> elevators = elevatorSystem.getElevators();
        for (int i = 0; i < elevators.size(); i++) {
            IElevator elevator = elevators.get(i);
            System.out.println(i + " " + elevator);
        }
    }

    private static void call(String[] splitLine) throws InvalidCommandArguments {
        checkArgumentsNumber(splitLine, 2);
        Direction direction = switch (splitLine[2]) {
            case "UP" -> Direction.UP;
            case "DOWN" -> Direction.DOWN;
            default -> throw new InvalidCommandArguments("invalid direction argument");
        };
        try {
            System.out.println("Assigned elevator nr: " +
                    elevatorSystem.call(Integer.parseInt(splitLine[1]), direction)
            );
        } catch (NumberFormatException e) {
            throw new InvalidCommandArguments("wrong number format");
        }
    }
    private static void chooseDestination(String[] splitLine) throws InvalidCommandArguments  {
        checkArgumentsNumber(splitLine, 2);
        try {
                elevatorSystem.chooseDestination(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]));
        } catch (NumberFormatException e) {
            throw new InvalidCommandArguments("wrong number format");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandArguments("wrong elevator number");
        }

    }

    private static void simulate(String[] splitLine) throws InvalidCommandArguments {
        checkArgumentsNumber(splitLine, 1);
        try {
            elevatorSystem.simulate(Integer.parseInt(splitLine[1]));
        } catch (NumberFormatException e) {
            throw new InvalidCommandArguments("wrong number format");
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandArguments(e.getMessage());
        }
    }
}
