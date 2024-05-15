package ir.ac.kntu;

import java.util.Scanner;

public class MainMenu {
    private static MainMenu instance = new MainMenu();

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        return instance;
    }

    public void printTheMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- User");
        System.out.println("2- Support");
        System.out.println("3- Exit");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }
    public void scanTheInput(Scanner scanner) {
        Role[] roles = Role.values();
        int input = scanTheInput(scanner);
        if (input >= 0 && input < roles.length) {
            return roles[input];
        }
        return Role.UNDEFINED;
    }
}




/*private static Menu instance = new Menu();

	private Menu() {
	}

	public static Menu getInstance() {
		return instance;
	}

	public void printTheMenu() {
		System.out.println("***********************************");
		System.out.println("Shape options:");
		System.out.println("1-Ellipse.");
		System.out.println("2-Circle.");
		System.out.println("3-Triangle.");
		System.out.println("4-Equilateral Triangle.");
		System.out.println("5-Sphere.");
		System.out.println("6-All.");
		System.out.println("7-Exit.");
		System.out.println("***********************************");
		System.out.print("\r\nPlease select your choice: ");
	}

	public ShapeProgram.Option getOption() {
		ShapeProgram.Option[] options = ShapeProgram.Option.values();
		int userInput = ScannerWrapper.getInstance().nextInt();
		userInput--;
		if (userInput >= 0 && userInput < options.length) {
			return options[userInput];
		}
		return ShapeProgram.Option.UNDEFINED;
	}
*/