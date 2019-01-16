/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectpatternlocktry4;

/**
 *
 * @author Wajeeha Parker
 */
public class table {
    int state;
    String s;
    public table(int n, String s)
    {
        this.state=n;
        this.s=s;
    }
    public String toString()
    {
        return this.state+" "+ this.s;
    }
}