package actions;

import entities.Player;

public class OpeningInventoryAction implements Action {
    
    @Override
    public boolean execute(Player player) {
        if (player == null) {
            System.out.println("Player not found");
            return false;
        }
        player.getPlayerInventory().printInventory();
        return true;
    }
}