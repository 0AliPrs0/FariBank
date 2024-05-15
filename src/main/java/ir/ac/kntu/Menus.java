package ir.ac.kntu;

public class Menus {
    private static Menus instance = new Menus();

    private Menus() {
    }

    public static Menus getInstance() {
        return instance;
    }

    public void printTheMainMenu() {
        System.out.println();
        System.out.println("***********************");
        System.out.println("1- User");
        System.out.println("2- Support");
        System.out.println("3- Exit");
        System.out.println("***********************");
        System.out.println();
        System.out.print("Select your roles: ");
    }
    public Role getOptionMainMenu() {
        Role[] roles = Role.values();
        int input = ScannerWrapper.getInstance().nextInt();
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