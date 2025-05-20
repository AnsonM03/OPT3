package example.org.Templates;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers(boolean antwoordCorrect);
}
