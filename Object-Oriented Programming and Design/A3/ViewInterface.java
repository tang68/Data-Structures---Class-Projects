package miniTwitter;

public interface ViewInterface {

	public void follow(String id);
    public void notifyObservers(String tweet);

}
