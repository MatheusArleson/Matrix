package br.com.xavier.matrix.util.messages;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class IndexAwareSet<E> implements Set<E> {

	private LinkedHashSet<E> wrappedSet;
	
	//XXX CONSTRUCTOR
	
	public IndexAwareSet(LinkedHashSet<E> set) {
		if(set == null){
			throw new NullPointerException();
		}
		
		this.wrappedSet = set;
	}

	//XXX OVERRIDE METHODS
	
	@Override
	public int size() {
		return wrappedSet.size();
	}

	@Override
	public boolean isEmpty() {
		return wrappedSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return wrappedSet.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return wrappedSet.iterator();
	}

	@Override
	public Object[] toArray() {
		return wrappedSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return wrappedSet.toArray(a);
	}

	@Override
	public boolean add(E e) {
		return wrappedSet.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return wrappedSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return wrappedSet.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return wrappedSet.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return wrappedSet.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return wrappedSet.removeAll(c);
	}

	@Override
	public void clear() {
		wrappedSet.clear();
	}

	//XXX METHODS
	public int getIndex(E e) {
		int result = 0;
		for (E entry : wrappedSet) {
			if (entry.equals(e)) { return result; }
			result++;
		}
		
		return -1;
	}
}
