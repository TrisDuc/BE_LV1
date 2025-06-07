/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage.controllers;

import java.util.ArrayList;
import manage.model.IMenu;
import manage.model.Utils;

/**
 *
 * @author 01duc
 */
public class MenuController extends ArrayList<String> implements IMenu{

    public MenuController() {
    }

    // must implement all abstract method of I_Menu interface

    @Override
    public void addItem(String s) {
        this.add(s);
    }

    @Override
    public void showMenu() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }

    @Override
    public boolean confirmYesNo(String welcome) {
        boolean result = false;
        result = Utils.confirmYesNo(welcome);
        return result;
    }

    @Override
    public int getChoice() {
        return Utils.getInt("Input your choice:", 1, 8);
    }
}
