/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slobodastudio.tasks;

/**
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
public interface ITask {
    public void loadFromFile();
    public void solution();
    public void writeToFile();
}
