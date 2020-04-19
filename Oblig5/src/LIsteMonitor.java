import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LIsteMonitor {
	Liste<String> list;
	Lock lock = new ReentrantLock();
	public LIsteMonitor(Liste<String>  l) {
		list = l;
		
	}
	

	public void addString(String s) {
		lock.lock();
		list.leggTil(s);
		lock.unlock();
	}
}
