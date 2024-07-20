package pl.monopoly.model;

public class UnBuyAbleField extends Field{

    public UnBuyAbleField(Game game) {
        super(game);
    }

    @Override
    public void action(Player player, Board board) {
        game.getBoardView().incomeTaxesInformation(player);
        player.pay(200);
    }

    @Override
    public boolean isBuyAble() {
        return false;
    }
}
