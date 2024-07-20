package pl.monopoly.model;

import javax.swing.*;

public class BuyAbleField extends Field{
    private Player owner;
    private final FieldSet set;
    private final int setSize;
    private final int price;
    private final int baseTax;
    private int houses = 0;

    // methods
    public BuyAbleField(Game game, FieldSet set, int setSize, int price, int tax) {
        super(game);
        this.set = set;
        this.setSize = setSize;
        this.price = price;
        this.baseTax = tax;
    }

    @Override
    public void action(Player player, Board board) {
        int answer;

        if (owner == null && player.getMoney() > price){

            answer = game.getBoardView().askForBuyDialog(this);

            if (answer == JOptionPane.OK_OPTION) {
                player.pay(price);
                owner = player;
            }

        } else if (owner != null && !player.toString().equals(owner.toString())) {

            game.getBoardView().payRentInformation(this);
            player.setMoney(player.getMoney()- getFullTax());

            owner.setMoney(owner.getMoney()+ getFullTax());

        } else if (owner != null && houses < 4 && player.getMoney() > 350){
            answer = game.getBoardView().buildHouseDialog();

            if (answer == JOptionPane.OK_OPTION) {
                player.pay(350);
                houses++;
            }
        }
    }

    @Override
    public boolean isBuyAble() {
        return true;
    }

    // get/set
    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public void clearOwner() {
        this.owner = null;
    }

    public int getFullTax() {
        if (houses == 0 && game.hasAllSet(owner, set)) {
            return baseTax*2;
        }

        return baseTax + houses*250;
    }

    public FieldSet getSet() {
        return set;
    }

    @Override
    public String toString() {
        return "BuyAbleField{" +
                "owner=" + owner +
                ", set=" + set +
                ", setSize=" + setSize +
                ", price=" + price +
                ", tax=" + baseTax +
                '}';
    }

    public int getHouses() {
        return houses;
    }
}
