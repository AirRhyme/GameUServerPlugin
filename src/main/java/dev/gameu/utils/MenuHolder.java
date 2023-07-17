package dev.gameu.utils;

import org.bukkit.inventory.Inventory;

public class MenuHolder implements org.bukkit.inventory.InventoryHolder {

    private final int currentPage;

    public MenuHolder(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

}
