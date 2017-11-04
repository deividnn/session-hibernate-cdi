/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author prog1
 */
public class ErroException extends Exception {

    public ErroException(String msg) {
        super(msg);
    }

    public ErroException(String msg, Throwable t) {
        super(msg, t);
    }
}
