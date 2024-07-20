package pl.monopoly.view;

import pl.monopoly.model.BuyAbleField;
import pl.monopoly.model.Player;

import javax.swing.*;


// must ask player is he want to buy a new field and send back answer
// sluzy do tego zeby komponenty logiczne mogly wysylac sygnal widoczny graficznie i otrzymywac odpowiedz
public class BoardView {

    // methods
    public int askForBuyDialog(BuyAbleField buyAbleField) {

        return JOptionPane.showConfirmDialog(null, "Do you want to buy this field for " + buyAbleField.getPrice() + "?" , "BUY FIELD", JOptionPane.YES_NO_OPTION);
    }

    public void payRentInformation(BuyAbleField buyAbleField) {

        JOptionPane.showMessageDialog(null, "This field is already owned!\nYou have to pay a rent of " + buyAbleField.getFullTax() + "$ to the owner", "OTHER PLAYER'S FIELD", JOptionPane.INFORMATION_MESSAGE);
    }

    public int buildHouseDialog() {

        return JOptionPane.showConfirmDialog(null, "You already owned this field.\nDo you want to build a house for 350$?", "CONSTRUCTION CREW", JOptionPane.YES_NO_OPTION);
    }

    public void getLoseInformation(Player player) { //TODO
        JOptionPane.showMessageDialog(null, "Player " + player.getColor() + " lost the game.", "GAME RESULTS", JOptionPane.INFORMATION_MESSAGE);
    }

    public void incomeTaxesInformation(Player player) {
        JOptionPane.showMessageDialog(null, "Player " + player.getColor() + " has to pay 200$ of taxes", "TAXES INCOME", JOptionPane.INFORMATION_MESSAGE);
    }

    public int getWinInformation(Player player) {
        return JOptionPane.showOptionDialog(null,
                "Player " + player.getColor() + " won the game.",
                "GAME RESULTS",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"MENU" , "QUIT"},
                0);
    }

}
