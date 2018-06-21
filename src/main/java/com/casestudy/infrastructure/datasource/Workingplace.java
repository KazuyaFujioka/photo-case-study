package com.casestudy.infrastructure.datasource;

import java.io.File;

public class Workingplace {

    static File directory = new File("workingplace");
    static File debugging = new File(directory.getPath() + File.separator + "debugging");

    Workingplace(File file) {
        this.directory = file;
    }

    public String path() {
        return directory.getPath();
    }

    public static void create() {
        if(directory.exists()) return;
        directory.mkdir();
    }

    public static Workingplace forDebugging() {
        return new Workingplace(debugging);
    }
}