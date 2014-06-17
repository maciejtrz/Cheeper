import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import com.byteslounge.collections.ReversedIterator;

public final class CollectionHelper {
	
	private CollectionHelper() {
		// Prevent instantiation
	}
	
	public static <T extends Comparable<? super T>> ArrayList<T> mergeSorted(List<List<T>> lists) {
		
		List<Iterator<T>> iterators = new ArrayList<Iterator<T>>();
		ArrayList<T> output = new ArrayList<T>();
		Comparator<Pair<Integer,T>> comparator = new PairComparator<Integer, T>();
		PriorityQueue<Pair<Integer, T>> heap = new PriorityQueue<Pair<Integer, T>>(lists.size(), comparator);
		
		// Initialise heap
		for (int i = 0; i<lists.size(); ++i) {
			// Iterate in reverse to get the most recent posts first
			Iterator<T> it = (new ReversedIterator<T>(lists.get(i))).iterator();
			iterators.add(it);
			if (it.hasNext()) {
				heap.add(new Pair<Integer, T>(i, it.next()));
			}
		}
		
		// Poll minimum element and replace with an element from the same array
		while (!heap.isEmpty()) {
			Pair<Integer, T> min = heap.poll();
			int index = min.getFst();
			// Unless the iterator has reached the end of the array
			if (iterators.get(index).hasNext()) {
				Pair<Integer, T> newPair = new Pair<Integer, T>(index, iterators.get(index).next());
				heap.add(newPair);
			}
			output.add(min.getSnd());
		}
		
		return output;

	}

}

class PairComparator<F, T extends Comparable<? super T>> implements Comparator<Pair<F, T>> {

	@Override
	public int compare(Pair<F, T> o1, Pair<F, T> o2) {
		return o1.getSnd().compareTo(o2.getSnd());
	}

}
