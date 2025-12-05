public interface FileOperations{
    void saveToFile(String filename) throws java.io.IOException;
    void loadFromFile(String filename) throws java.io.IOException, java.io.FileNotFoundException;

}