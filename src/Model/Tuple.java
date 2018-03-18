package Model;


import Model.Exceptions.MyException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tuple {
    private String filename;
    private BufferedReader file;

    public Tuple(String f) throws MyException {
        filename=f;
        try {
            file = new BufferedReader(new FileReader(filename));
        }
        catch(IOException e)
        {
            throw new MyException("File "+f+" could not be opened!\n"+e);
        }
    }

    public String getFilename() {
        return filename;
    }

    public BufferedReader getFile() {
        return file;
    }

    @Override
    public String toString() {
        return filename;
    }
}
