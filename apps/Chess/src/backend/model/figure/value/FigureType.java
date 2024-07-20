package backend.model.figure.value;

public enum FigureType  {
   PAWN(1),
   ROOK(5),
   KNIGHT(3),
   BISHOP(3),
   QUEEN(9),
   KING(3),
   UNKNOWN(0);

   private int strength;

   FigureType(int strength) {
      this.strength = strength;
   }

   public int getStrength() {
      return strength;
   }

}
