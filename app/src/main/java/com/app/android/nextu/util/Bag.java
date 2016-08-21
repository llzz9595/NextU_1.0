package com.app.android.nextu.util;

import java.util.Iterator;


public class Bag<T> implements Iterable<T> {

	Node first;
	
	
	
	int size;
	public Iterator<T> iterator() {

		
		return new bagIterator<T>();
	}

	int i=size;
	private class bagIterator<T> implements Iterator<T>
	{
	private Node current=first;
     
		@Override
		public boolean hasNext() {
			
	//		return i>0;
			return current!=null;
		}

		@Override
		public T next() {
			T item=(T) current.item;
			//T item =(T) a[--i];
			current=current.next;
			return  item;
		}

		@Override
		public void remove() {

		}

	}
	private class Node<T>
	{
		T item ;
		Node next;
	}
	public void add(T item) {
		
		Node oldfirst=first;
		first=new Node();
		first.item=item;
		first.next=oldfirst;
		
	}

	
}
