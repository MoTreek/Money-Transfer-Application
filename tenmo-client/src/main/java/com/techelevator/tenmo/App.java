package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import io.cucumber.java.cy_gb.A;
import io.cucumber.java.eo.Do;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final UserService userService = new UserService();
    private final TransferService transferService = new TransferService();
    private final AccountService accountService = new AccountService();

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }

    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        } else {
            transferService.setAuthToken(currentUser.getToken());
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

    private void viewCurrentBalance() {
        Double balance = accountService.getBalance(currentUser);
        if (balance != null) {
            System.out.println("Your current account balance is: " + balance);

        } else {
            consoleService.printErrorMessage();
        }
    }

    private void viewTransferHistory() {
        Account account = accountService.getAccount(currentUser);
        Transfer[] transfers;
        transfers = transferService.listTransfersByAccount(account);
        for (int i = 0; i < transfers.length; i++){
            if(transfers[i].getTransfer_status_id() > 1) {
                System.out.println(transfers[i].toString());
            }
        }
    }

    private void viewPendingRequests() {
        Account account = accountService.getAccount(currentUser);
        Transfer[] transfersList;
        transfersList = transferService.listTransfersByAccount(account);
        for (int i = 0; i < transfersList.length; i++){
            if(transfersList[i].getTransfer_status_id() == 1) {
                System.out.println(transfersList[i].toString());
            }
        }
    }

    private void sendBucks() {
        User[] users = userService.retrieveAllUsers();
        if (users != null) {
            consoleService.printUserMenu(users);
            int toUserId = consoleService.promptForInt("Enter UserID of user you are sending to (0 to cancel): ");
            if (toUserId != 0) {
                BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");
                int fromUserId = currentUser.getUser().getId();
                Transfer newTransfer = new Transfer(2, 2, fromUserId, toUserId,amount);
                Transfer transfer = transferService.createTransfer(newTransfer);
                if (transfer != null) {
                    System.out.println(amount + " dollars were sent to user " + toUserId);
                } else {
                    consoleService.printErrorMessage();
                }
            }
        } else {
            consoleService.printErrorMessage();
        }
    }


    
    private void requestBucks() {
        User[] users = userService.retrieveAllUsers();
        if (users != null) {
            consoleService.printUserMenu(users);
            int fromUserId = consoleService.promptForInt("Enter ID of user you are requesting from (0 to cancel): ");
            BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");
            consoleService.printUserMenu(users);
            int toUserId = currentUser.getUser().getId();
            //Double check line below
            Transfer newTransfer = new Transfer(2, 2, fromUserId, toUserId,amount);
            Transfer transfer = transferService.createTransfer(newTransfer);
            if (transfer != null) {
                System.out.println(amount + " dollars were requested from user " + toUserId);
            } else {
                consoleService.printErrorMessage();
            }
        } else {
            consoleService.printErrorMessage();
        }
    }
}
