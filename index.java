/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.game;

/**
 *
 * @author USER
 */
import javax.swing.JFrame;
public class index {

    public static void main(String[] args) {
        JFrame obj = new JFrame();//frame
        GamePlay gameplay = new GamePlay();// obj of gameplay class
        obj.setBounds(10,10,700,600);//size of frame
        obj.setTitle("Brick Breaker");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
