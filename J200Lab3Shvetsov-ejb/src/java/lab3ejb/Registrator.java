/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

/**
 *
 * @author User
 */
@Stateful
public class Registrator implements EJBDemo, StandardValues {

    private boolean registered;
    private int countMsg = 0;
    private String name = " ";

    @PostConstruct
    void init() {
        registered = false;
        countMsg = 0;
        System.out.println("--->registrator have been created ");
    }

    @Override
    public boolean login(String login, String psw) {
        registered = LOGIN.equals(login) && PSW.equals(psw);
        return registered;
    }

    @Override
    public String getMessage(String login) {
        if (registered) {
            ++countMsg;
            if (countMsg > 3) {
                registered = false;
                countMsg = 0;
                return MSG;
            }
            return name + ", this is test message # " + countMsg;
        } else {
            return MSG;
        }
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
