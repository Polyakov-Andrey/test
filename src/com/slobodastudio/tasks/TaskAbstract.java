package com.slobodastudio.tasks;


import com.slobodastudio.tasks.ITask;
import java.io.File;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrey Polyakov
 */
public class TaskAbstract{    
    
    File fileNameIn;
    File fileNameOut;

    /**
     * @return the fileNameIn
     */
    public File getFileNameIn() {
        return fileNameIn;
    }

    /**
     * @param fileNameIn the fileNameIn to set
     */
    public void setFileNameIn(File fileNameIn) {
        this.fileNameIn = fileNameIn;
    }

    /**
     * @return the fileNameOut
     */
    public File getFileNameOut() {
        return fileNameOut;
    }

    /**
     * @param fileNameOut the fileNameOut to set
     */
    public void setFileNameOut(File fileNameOut) {
        this.fileNameOut = fileNameOut;
    }

}
