package example.org;

public interface Observable {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(boolean antwoordCorrect);
}
