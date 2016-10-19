package com.andy.util;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author ming
 * @description 延迟队列
 *
 * @param <K>
 * @param <V>
 */
public class Cache<K, V> {

	public ConcurrentHashMap<K, V> map = new ConcurrentHashMap<K, V>();
	public DelayQueue<DelayedItem<K>> queue = new DelayQueue<DelayedItem<K>>();

	public V get(K key) {
		return map.get(key);
	}

	public void put(K k, V v, long liveTime, TimeUnit unit) {
		V v2 = map.put(k, v);
		long nanoTime = TimeUnit.NANOSECONDS.convert(liveTime, unit);
		DelayedItem<K> tmpItem = new DelayedItem<K>(k, nanoTime);
		if (v2 != null) {
			queue.remove(tmpItem);
		}
		queue.put(tmpItem);
	}

	public Cache() {
		Thread t = new Thread() {
			@Override
			public void run() {
				dameonCheckOverdueKey();
			}
		};
		t.setDaemon(true);
		t.start();
	}

	public void dameonCheckOverdueKey() {
		while (true) {
			DelayedItem<K> delayedItem = queue.poll();
			if (delayedItem != null) {
				map.remove(delayedItem.getT());
				System.out.println(System.nanoTime() + " remove " + delayedItem.getT() + " from cache");
			}
			try {
				Thread.sleep(300);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();
		int cacheNumber = 10;
		int liveTime = 0;
		Cache<String, Integer> cache = new Cache<String, Integer>();

		String key = 0 + "";
		Integer value = 0;
		cache.put(key, value, 3, TimeUnit.SECONDS);

		// for (int i = 0; i < cacheNumber; i++) {
		// liveTime = random.nextInt(3000);
		// System.out.println(i + " " + liveTime);
		// cache.put(i + "", i, random.nextInt(liveTime));
		// if (random.nextInt(cacheNumber) > 7) {
		// liveTime = random.nextInt(3000);
		// System.out.println(i + " " + liveTime);
		// cache.put(i + "", i, random.nextInt(liveTime));
		// }
		// }

		Thread.sleep(1000);
		System.out.println(cache.get(key));

		Thread.sleep(1000);
		System.out.println(cache.get(key));

		Thread.sleep(1000);
		System.out.println(cache.get(key));

		Thread.sleep(1000);
		System.out.println(cache.get(key));
	}

}

class DelayedItem<T> implements Delayed {

	private T t;
	private long liveTime;
	private long removeTime;

	public DelayedItem(T t, long liveTime) {
		this.setT(t);
		this.liveTime = liveTime;
		this.removeTime = TimeUnit.NANOSECONDS.convert(liveTime, TimeUnit.NANOSECONDS) + System.nanoTime();
	}

	public int compareTo(Delayed o) {
		if (o == null)
			return 1;
		if (o == this)
			return 0;
		if (o instanceof DelayedItem) {
			DelayedItem<T> tmpDelayedItem = (DelayedItem<T>) o;
			if (liveTime > tmpDelayedItem.liveTime) {
				return 1;
			} else if (liveTime == tmpDelayedItem.liveTime) {
				return 0;
			} else {
				return -1;
			}
		}
		long diff = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
		return diff > 0 ? 1 : diff == 0 ? 0 : -1;
	}

	public long getDelay(TimeUnit unit) {
		return unit.convert(removeTime - System.nanoTime(), unit);
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public int hashCode() {
		return t.hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof DelayedItem) {
			return object.hashCode() == hashCode() ? true : false;
		}
		return false;
	}

}
